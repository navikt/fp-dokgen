package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.jsonschema;

import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.lesRessursSomString;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;

import jakarta.enterprise.context.Dependent;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.exceptions.DokgenSchemaValidationException;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil;

@Dependent
public class JsonSchemaTjeneste {

    private static final ConcurrentHashMap<String, JsonSchema> JSON_SCHEMA_CACHE = new ConcurrentHashMap<>();
    private static final JsonSchemaFactory JSON_SCHEMA_FACTORY = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);

    JsonSchemaTjeneste() {
        // for CDI
    }

    public void validerDataMotSchema(Map<String, Object> data, Path schemaPath) {
        Objects.requireNonNull(data, "Ved validering av data-json");
        Objects.requireNonNull(schemaPath, "schemaPath ved validering av data-json");
        validerDataMotSchema(data, hentSchema(schemaPath));
    }

    void validerDataMotSchema(Map<String, Object> data, JsonSchema schema) {
        var jsonNode = JacksonUtil.JSON_MAPPER.convertValue(data, JsonNode.class);
        var failures = schema.validate(jsonNode);

        if (!failures.isEmpty()) {
            throw new DokgenSchemaValidationException(failures.toString());
        }
    }

    private JsonSchema hentSchema(Path schemaPath) {
        var cacheKey = cacheKey(schemaPath);
        return JSON_SCHEMA_CACHE.computeIfAbsent(cacheKey, _ -> {
            var schemaString = lesRessursSomString(schemaPath);
            return JSON_SCHEMA_FACTORY.getSchema(schemaString);
        });
    }

    private String cacheKey(Path schemaPath) {
        return schemaPath.toString();
    }
}
