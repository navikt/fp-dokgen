package no.nav.foreldrepenger.fpdokgen.server.app.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.config.RoutesConfig;
import io.javalin.http.HttpStatus;
import jakarta.enterprise.inject.se.SeContainer;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.rest.HealtCheckRest;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.rest.PrometheusRestService;

public final class InternalApiConfig {

    private static final Logger LOG = LoggerFactory.getLogger(InternalApiConfig.class);
    public static final String API_URI = "/internal";

    private InternalApiConfig() {
    }

    public static void register(RoutesConfig routes, SeContainer container) {
        LOG.info("Initialiserer: {}", API_URI);

        routes.get(API_URI + "/health/isAlive", ctx -> {
            if (container.select(HealtCheckRest.class).get().isAlive()) {
                ctx.status(HttpStatus.OK);
            } else {
                LOG.info("/isAlive NOK.");
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });

        routes.get(API_URI + "/health/isReady", ctx -> {
            if (container.select(HealtCheckRest.class).get().isReady()) {
                ctx.status(HttpStatus.OK);
            } else {
                LOG.info("/isReady NOK.");
                ctx.status(HttpStatus.SERVICE_UNAVAILABLE);
            }
        });

        routes.get(API_URI + "/health/preStop", ctx -> ctx.status(HttpStatus.OK));

        routes.get(API_URI + "/metrics/prometheus", ctx -> {
            ctx.contentType("text/plain");
            ctx.result(container.select(PrometheusRestService.class).get().prometheus());
        });

        LOG.info("Ferdig med initialisering av {}", API_URI);
    }
}
