package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.jsonschema;

import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.lesRessursSomString;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.SpecificationVersion;

import jakarta.enterprise.context.Dependent;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.exceptions.DokgenSchemaValidationException;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil;

@Dependent
public class JsonSchemaTjeneste {

    private static final ConcurrentHashMap<String, Schema> JSON_SCHEMA_CACHE = new ConcurrentHashMap<>();
    private static final SchemaRegistry SCHEMA_REGISTRY = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_2020_12);

    JsonSchemaTjeneste() {
        // for CDI
    }

    public void validerDataMotSchema(Map<String, Object> data, Path schemaPath) {
        Objects.requireNonNull(data, "Ved validering av data-json");
        Objects.requireNonNull(schemaPath, "schemaPath ved validering av data-json");
        validerDataMotSchema(data, hentSchema(schemaPath));
    }

    void validerDataMotSchema(Map<String, Object> data, Schema schema) {
        var jsonNode = JacksonUtil.JSON_MAPPER.convertValue(data, JsonNode.class);
        var failures = schema.validate(jsonNode);

        if (!failures.isEmpty()) {
            throw new DokgenSchemaValidationException(failures.toString());
        }
    }

    private Schema hentSchema(Path schemaPath) {
        var cacheKey = cacheKey(schemaPath);
        return JSON_SCHEMA_CACHE.computeIfAbsent(cacheKey, _ -> {
            var schemaString = lesRessursSomString(schemaPath);
            return SCHEMA_REGISTRY.getSchema(schemaString);
        });
    }

    private String cacheKey(Path schemaPath) {
        return schemaPath.toString();
    }
}
