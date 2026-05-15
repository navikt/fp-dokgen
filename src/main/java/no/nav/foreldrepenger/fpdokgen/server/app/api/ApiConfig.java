package no.nav.foreldrepenger.fpdokgen.server.app.api;

import java.util.Set;

import io.javalin.config.RoutesConfig;
import io.javalin.http.Context;
import jakarta.enterprise.inject.se.SeContainer;
import no.nav.foreldrepenger.fpdokgen.server.RequestValidator;
import no.nav.foreldrepenger.fpdokgen.tjenester.v1.DokumentGeneratorRequest;
import no.nav.foreldrepenger.fpdokgen.tjenester.v1.DokumentGeneratorRestTjeneste;

/**
 * Registrerer publike /api-ruter på Javalin.
 *
 * <p>Klassenavnet er beholdt for å minimere støy i tester (fp-felles-konvensjon
 * for {@code RestApiTester}-introspeksjon), men dette er ikke lenger en JAX-RS
 * {@code Application}.
 */
public final class ApiConfig {

    public static final String API_URI = "/api";

    private ApiConfig() {
    }

    public static void register(RoutesConfig routes, SeContainer container) {
        routes.before(API_URI + "/*", AuthorizationFilter::before);
        routes.after(API_URI + "/*", AuthorizationFilter::after);

        routes.post(API_URI + "/v1/dokument/generer/pdf", ctx -> {
            var req = readAndValidate(ctx);
            var pdf = container.select(DokumentGeneratorRestTjeneste.class).get().genererPdfDokument(req);
            ctx.contentType("application/pdf");
            ctx.header("Content-Disposition", "inline; filename=\"" + req.malNavn() + ".pdf\"");
            applyNoCache(ctx);
            ctx.result(pdf);
        });

        routes.post(API_URI + "/v1/dokument/generer/html", ctx -> {
            var req = readAndValidate(ctx);
            var html = container.select(DokumentGeneratorRestTjeneste.class).get().genererHtmlDokument(req);
            ctx.contentType("text/html; charset=UTF-8");
            applyNoCache(ctx);
            ctx.result(html);
        });
    }

    /**
     * Klasser som tidligere var registrert som JAX-RS resources.
     * Brukes av tester som verifiserer DTO-validering.
     */
    public static Set<Class<?>> getEndpointClasses() {
        return Set.of(DokumentGeneratorRestTjeneste.class);
    }

    private static DokumentGeneratorRequest readAndValidate(Context ctx) {
        return RequestValidator.validate(ctx.bodyAsClass(DokumentGeneratorRequest.class));
    }

    private static void applyNoCache(Context ctx) {
        ctx.header("Cache-Control", "no-cache, no-store, must-revalidate");
    }
}
