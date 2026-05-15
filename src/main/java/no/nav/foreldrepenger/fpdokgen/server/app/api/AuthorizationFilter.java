package no.nav.foreldrepenger.fpdokgen.server.app.api;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import no.nav.vedtak.exception.VLException;
import no.nav.vedtak.klient.http.CommonHttpHeaders;
import no.nav.vedtak.log.mdc.FnrUtils;
import no.nav.vedtak.log.mdc.MDCOperations;
import no.nav.vedtak.sikkerhet.kontekst.KontekstHolder;
import no.nav.vedtak.sikkerhet.kontekst.RequestKontekst;
import no.nav.vedtak.sikkerhet.oidc.config.ConfigProvider;
import no.nav.vedtak.sikkerhet.oidc.token.OpenIDToken;
import no.nav.vedtak.sikkerhet.oidc.token.TokenString;
import no.nav.vedtak.sikkerhet.oidc.validator.JwtUtil;
import no.nav.vedtak.sikkerhet.oidc.validator.OidcTokenValidatorConfig;

/**
 * Validerer OIDC-token og krever system-kontekst på alle /api-kall.
 * Erstatter felles AuthenticationFilter + lokal AuthorizationFilter (som
 * tidligere kjørte som ContainerRequestFilter under Jersey).
 */
public final class AuthorizationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private AuthorizationFilter() {
    }

    public static void before(Context ctx) {
        try {
            if (KontekstHolder.harKontekst()) {
                KontekstHolder.fjernKontekst();
            }
            MDC.clear();
            setCallAndConsumerId(ctx);

            var tokenString = getTokenFromHeader(ctx)
                .orElseThrow(() -> new ForbiddenException("Mangler token"));
            validerTokenSetKontekst(tokenString);
            setUserAndConsumerId(KontekstHolder.getKontekst().getUid());

            if (!(KontekstHolder.getKontekst() instanceof RequestKontekst kontekst) || !kontekst.getIdentType().erSystem()) {
                throw new ForbiddenException("Kun systemkontekst er tillatt.");
            }
        } catch (ForbiddenException e) {
            LOG.warn("Avviser /api-kall: {}", e.getMessage());
            KontekstHolder.fjernKontekst();
            ctx.status(HttpStatus.FORBIDDEN);
            ctx.skipRemainingHandlers();
        } catch (VLException e) {
            LOG.warn("Autentiseringsfeil for /api-kall", e);
            KontekstHolder.fjernKontekst();
            ctx.status(HttpStatus.FORBIDDEN);
            ctx.skipRemainingHandlers();
        }
    }

    public static void after(Context ctx) {
        if (KontekstHolder.harKontekst()) {
            KontekstHolder.fjernKontekst();
        }
        MDC.clear();
    }

    private static void setCallAndConsumerId(Context ctx) {
        var callId = header(ctx, CommonHttpHeaders.HEADER_NAV_CALLID)
            .or(() -> header(ctx, CommonHttpHeaders.HEADER_NAV_ALT_CALLID))
            .or(() -> header(ctx, CommonHttpHeaders.HEADER_NAV_LOWER_CALL_ID))
            .orElseGet(MDCOperations::generateCallId);
        MDCOperations.putCallId(callId);
        header(ctx, CommonHttpHeaders.HEADER_NAV_CONSUMER_ID).ifPresent(MDCOperations::putConsumerId);
    }

    private static Optional<String> header(Context ctx, String name) {
        return Optional.ofNullable(ctx.header(name)).filter(s -> !s.isEmpty());
    }

    private static Optional<TokenString> getTokenFromHeader(Context ctx) {
        return Optional.ofNullable(ctx.header(AUTHORIZATION_HEADER))
            .filter(value -> value.startsWith(OpenIDToken.OIDC_DEFAULT_TOKEN_TYPE))
            .map(value -> value.substring(OpenIDToken.OIDC_DEFAULT_TOKEN_TYPE.length()))
            .map(TokenString::new);
    }

    private static void setUserAndConsumerId(String subject) {
        Optional.ofNullable(subject).ifPresent(MDCOperations::putUserId);
        if (MDCOperations.getConsumerId() == null && subject != null) {
            MDCOperations.putConsumerId(FnrUtils.maskFnr(subject));
        }
    }

    private static void validerTokenSetKontekst(TokenString tokenString) {
        var claims = JwtUtil.getClaims(tokenString.token());
        var configuration = ConfigProvider.getOpenIDConfiguration(JwtUtil.getIssuer(claims))
            .orElseThrow(() -> new ForbiddenException("Token mangler issuer claim"));
        var expiresAt = Optional.ofNullable(JwtUtil.getExpirationTime(claims))
            .orElseGet(() -> Instant.now().plusSeconds(300));
        var token = new OpenIDToken(configuration.type(), OpenIDToken.OIDC_DEFAULT_TOKEN_TYPE, tokenString, null, expiresAt.toEpochMilli());
        var validateResult = OidcTokenValidatorConfig.instance().getValidator(token.provider()).validate(token.primary());
        if (!validateResult.isValid()) {
            throw new ForbiddenException("Ugyldig token " + validateResult.getErrorMessage());
        }
        KontekstHolder.setKontekst(RequestKontekst.forRequest(validateResult.subject(), validateResult.compactSubject(),
            validateResult.identType(), token, validateResult.oid(), validateResult.getGrupper()));
    }

    private static final class ForbiddenException extends RuntimeException {
        ForbiddenException(String message) {
            super(message);
        }
    }
}
