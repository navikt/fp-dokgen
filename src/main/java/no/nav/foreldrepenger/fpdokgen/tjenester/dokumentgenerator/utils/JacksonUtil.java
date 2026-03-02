package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import no.nav.vedtak.mapper.json.DefaultJsonMapper;

public class JacksonUtil {

    public static final JsonMapper JSON_MAPPER = DefaultJsonMapper.getJsonMapper();

    private JacksonUtil() {
        // Utility class.
    }

    public static Map<String, Object> getJsonMapFromString(String jsonAsString) {
        try {
            return JSON_MAPPER.readValue(jsonAsString, Map.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Kunne ikke parse JSON string.", e);
        }
    }
}
