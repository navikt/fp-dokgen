package no.nav.foreldrepenger.fpdokgen.server;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.config.RoutesConfig;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import no.nav.vedtak.exception.VLException;
import no.nav.vedtak.feil.Feilkode;
import no.nav.vedtak.log.mdc.MDCOperations;
import no.nav.vedtak.log.util.LoggerUtils;

final class ExceptionMappers {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionMappers.class);

    private ExceptionMappers() {
    }

    static void register(RoutesConfig routes) {
        routes.exception(ConstraintViolationException.class, (ex, ctx) -> {
            var melding = formatViolations(ex);
            LOG.warn("Valideringsfeil: {}", melding);
            ctx.status(400).json(feilDto(400, Feilkode.VALIDERING.name(), "Valideringsfeil for felt " + melding));
        });

        routes.exception(VLException.class, (ex, ctx) -> {
            var status = ex.getStatusCode();
            LOG.warn("VLException: {}", LoggerUtils.removeLineBreaks(safeMessage(ex)), ex);
            ctx.status(status).json(feilDto(status, ex.getFeilkode(), safeMessage(ex)));
        });

        routes.exception(Exception.class, (ex, ctx) -> {
            LOG.warn("Fikk uventet feil: {}", LoggerUtils.removeLineBreaks(safeMessage(ex)), ex);
            ctx.status(500).json(feilDto(500, Feilkode.GENERELL.name(), safeMessage(ex)));
        });
    }

    private static String formatViolations(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
            .map(ExceptionMappers::formatViolation)
            .sorted()
            .collect(Collectors.joining(", "));
    }

    private static String formatViolation(ConstraintViolation<?> v) {
        var root = Optional.ofNullable(v.getRootBeanClass())
            .map(Class::getSimpleName)
            .map(rbn -> rbn.replace("$Proxy$_$$_WeldClientProxy", "").concat("."))
            .orElse("");
        var field = Optional.ofNullable(v.getPropertyPath()).map(Path::toString).orElse("null");
        return root + field + ": " + v.getMessage();
    }

    private static String safeMessage(Throwable ex) {
        var input = ex.getMessage();
        return input != null ? LoggerUtils.removeLineBreaks(input) : "";
    }

    private static FeilDto feilDto(int status, String feilkode, String melding) {
        if (MDCOperations.getCallId() == null) {
            MDCOperations.putCallId();
        }
        return new FeilDto(status, feilkode, melding, MDCOperations.getCallId());
    }

    record FeilDto(int status, String feilkode, String melding, String callId) {
    }
}
