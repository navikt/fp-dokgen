package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils;

import java.util.Map;

import no.nav.vedtak.mapper.json.DefaultJson3Mapper;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

public class JacksonUtil {

    public static final JsonMapper JSON_MAPPER = DefaultJson3Mapper.getJsonMapper();

    private JacksonUtil() {
        // Utility class.
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getJsonMapFromString(String jsonAsString) {
        try {
            return JSON_MAPPER.readValue(jsonAsString, Map.class);
        } catch (JacksonException e) {
            throw new IllegalArgumentException("Kunne ikke parse JSON string.", e);
        }
    }
}
