package no.nav.foreldrepenger.fpdokgen.server.app.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.rest.HealtCheckRest;
import no.nav.foreldrepenger.fpdokgen.server.app.internal.rest.PrometheusRestService;

@ApplicationPath(InternalApiConfig.API_URI)
public class InternalApiConfig extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(InternalApiConfig.class);
    public static final String API_URI = "/internal";

    public InternalApiConfig() {
        LOG.info("Initialiserer: {}", API_URI);

    }

    @Override
    public Set<Class<?>> getClasses() {
        var classes = Set.of(HealtCheckRest.class, PrometheusRestService.class);
        LOG.info("Ferdig med initialisering av {}", API_URI);
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
    }
}
