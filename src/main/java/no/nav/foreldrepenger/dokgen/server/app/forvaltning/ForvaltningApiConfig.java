package no.nav.foreldrepenger.dokgen.server.app.forvaltning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.GenericOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import no.nav.foreldrepenger.dokgen.server.exceptions.GeneralRestExceptionMapper;
import no.nav.foreldrepenger.dokgen.server.exceptions.JsonMappingExceptionMapper;
import no.nav.foreldrepenger.dokgen.server.exceptions.JsonParseExceptionMapper;
import no.nav.foreldrepenger.dokgen.server.exceptions.ValidationExceptionMapper;
import no.nav.foreldrepenger.dokgen.server.jackson.JacksonJsonConfig;
import no.nav.foreldrepenger.dokgen.tjenester.DokumentGeneratorRestTjeneste;
import no.nav.foreldrepenger.konfig.Environment;
import no.nav.vedtak.exception.TekniskException;

@ApplicationPath(ForvaltningApiConfig.API_URI)
public class ForvaltningApiConfig extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(ForvaltningApiConfig.class);
    public static final String API_URI = "/forvaltning/api";
    private static final Environment ENV = Environment.current();

    public ForvaltningApiConfig() {
        LOG.info("Initialiserer: {}", API_URI);
        initializeOpenApi();
    }

    private void initializeOpenApi() {
        var oas = new OpenAPI();
        var info = new Info().title(ENV.getNaisAppName())
            .version(Optional.ofNullable(ENV.imageName()).orElse("1.0"))
            .description("API for brev generering for foreldrepenger og svangerskapspenger")
            .license(new License().name("MIT").url("http://nav.no"));

        oas.info(info).addServersItem(new Server().url(ENV.getProperty("context.path", "/fpdokgen")));
        var oasConfig = new SwaggerConfiguration().openAPI(oas)
            .prettyPrint(true)
            .resourceClasses(getForvaltningClasses().stream().map(Class::getName).collect(Collectors.toSet()));
        try {
            new GenericOpenApiContextBuilder<>().openApiConfiguration(oasConfig).buildContext(true).read();
        } catch (OpenApiConfigurationException e) {
            throw new TekniskException("OPEN-API", e.getMessage(), e);
        }
    }

    @Override
    public Set<Class<?>> getClasses() {
        var classes = new HashSet<Class<?>>();
        // Sikkerhet
        // TODO: 🙈 aktiver sikkerhet for forvaltnings-API
        //      classes.add(AuthenticationFilter.class);
        //      classes.add(ForvaltningAuthorizationFilter.class);
        classes.add(OpenApiResource.class);
        classes.addAll(registerExceptionMappers());
        classes.add(JacksonJsonConfig.class);
        classes.addAll(getForvaltningClasses());
        LOG.info("Ferdig med initialisering av {}", API_URI);
        return classes;
    }

    Set<Class<?>> registerExceptionMappers() {
        return Set.of(GeneralRestExceptionMapper.class, ValidationExceptionMapper.class, JsonMappingExceptionMapper.class,
            JsonParseExceptionMapper.class);
    }

    private Set<Class<?>> getForvaltningClasses() {
        return Set.of(DokumentGeneratorRestTjeneste.class);
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Ref Jersey doc
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        properties.put(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        return properties;
    }

}
