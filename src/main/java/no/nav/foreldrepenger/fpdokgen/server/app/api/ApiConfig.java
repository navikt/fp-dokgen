package no.nav.foreldrepenger.fpdokgen.server.app.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import no.nav.foreldrepenger.fpdokgen.tjenester.v1.DokumentGeneratorRestTjeneste;
import no.nav.vedtak.server.rest.FpRestJackson2Feature;
import no.nav.vedtak.server.rest.GeneralRestExceptionMapper;

@ApplicationPath(ApiConfig.API_URI)
public class ApiConfig extends Application {

    public static final String API_URI = "/api";
    private static final Logger LOG = LoggerFactory.getLogger(ApiConfig.class);

    public ApiConfig() {
        GeneralRestExceptionMapper.setBrukerRettetApplikasjon(false);
        LOG.info("Initialiserer: {}", API_URI);
    }

    @Override
    public Set<Class<?>> getClasses() {
        var classes = new HashSet<Class<?>>();

        classes.add(FpRestJackson2Feature.class);
        classes.add(AuthorizationFilter.class);
        classes.addAll(getApplicationClasses());

        LOG.info("Ferdig med initialisering av {}", API_URI);
        return classes;
    }

    private Set<Class<?>> getApplicationClasses() {
        return Set.of(DokumentGeneratorRestTjeneste.class);
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Ref Jersey doc
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        properties.put(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        properties.put("jersey.config.server.wadl.disableWadl", true);
        return properties;
    }

}
