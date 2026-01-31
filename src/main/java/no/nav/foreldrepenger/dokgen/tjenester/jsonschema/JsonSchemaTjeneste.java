package no.nav.foreldrepenger.dokgen.tjenester.jsonschema;

import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.lesRessursSomString;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import jakarta.enterprise.context.ApplicationScoped;
import no.nav.foreldrepenger.dokgen.tjenester.exceptions.DokgenValidationException;
import no.nav.vedtak.mapper.json.DefaultJsonMapper;

@ApplicationScoped
public class JsonSchemaTjeneste {

    private static final ConcurrentHashMap<String, JsonSchema> JSON_SCHEMA_CACHE = new ConcurrentHashMap<>();
    private static final JsonSchemaFactory SCHEMA_FACTORY = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);

    JsonSchemaTjeneste() {
        // for CDI
    }

    public void validerDataMotSchema(Map<String, Object> data, Path schemaPath) {
        Objects.requireNonNull(data, "Ved validering av data-json");
        try {
            var schema = hentSchema(schemaPath);
            var jsonNode = DefaultJsonMapper.getJsonMapper().convertValue(data, JsonNode.class);
            var failures = schema.validate(jsonNode);

            if (!failures.isEmpty()) {
                var schemaErrors = failures.stream()
                    .collect(Collectors.toMap(f -> f.getInstanceLocation().toString(), ValidationMessage::getMessage,
                        (existing, replacement) -> existing + "; " + replacement));
                throw new DokgenValidationException(schemaErrors, failures.toString(), null);
            }
        } catch (DokgenValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Feil ved validering av JSON mot schema: " + schemaPath, e);
        }
    }

    private JsonSchema hentSchema(Path schemaPath) {
        var cacheKey = cacheKey(schemaPath);
        return JSON_SCHEMA_CACHE.computeIfAbsent(cacheKey, key -> {
            var schemaString = lesRessursSomString(Path.of(key));
            return SCHEMA_FACTORY.getSchema(schemaString);
        });
    }

    private String cacheKey(Path schemaPath) {
        return schemaPath.toString();
    }
}
