package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JacksonUtilTest {

    @Test
    void getJsonMapFromString_enkelJson() {
        var json = """
            {
                "navn": "Ola Nordmann",
                "alder": 30
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result)
            .isNotNull()
            .containsEntry("navn", "Ola Nordmann")
            .containsEntry("alder", 30);
    }

    @Test
    void getJsonMapFromString_nestedJson() {
        var json = """
            {
                "person": {
                    "fornavn": "Kari",
                    "etternavn": "Hansen"
                },
                "adresse": {
                    "gate": "Storgata 1",
                    "postnummer": "0123"
                }
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get("person")).isInstanceOf(Map.class);

        @SuppressWarnings("unchecked")
        var person = (Map<String, Object>) result.get("person");
        assertThat(person)
            .containsEntry("fornavn", "Kari")
            .containsEntry("etternavn", "Hansen");
    }

    @Test
    void getJsonMapFromString_medArray() {
        var json = """
            {
                "perioder": [
                    {"fra": "2026-01-01", "til": "2026-03-01"},
                    {"fra": "2026-04-01", "til": "2026-06-01"}
                ]
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result).isNotNull().containsKey("perioder");
        assertThat(result.get("perioder")).isInstanceOf(List.class);

        @SuppressWarnings("unchecked")
        var perioder = (List<Map<String, Object>>) result.get("perioder");
        assertThat(perioder).hasSize(2);
        assertThat(perioder.get(0)).containsEntry("fra", "2026-01-01");
    }

    @Test
    void getJsonMapFromString_tomJson() {
        var json = "{}";

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    void getJsonMapFromString_booleanOgNull() {
        var json = """
            {
                "aktiv": true,
                "inaktiv": false,
                "ukjent": null
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result)
            .containsEntry("aktiv", true)
            .containsEntry("inaktiv", false)
            .containsEntry("ukjent", null);
    }

    @Test
    void getJsonMapFromString_nummerTyper() {
        var json = """
            {
                "heltall": 42,
                "desimaltall": 3.14,
                "negativt": -100
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result)
            .containsEntry("heltall", 42)
            .containsEntry("negativt", -100);
        assertThat((Double) result.get("desimaltall")).isEqualTo(3.14);
    }

    @Test
    void getJsonMapFromString_ugyldigJson_kasterException() {
        var ugyldigJson = "dette er ikke gyldig json";

        assertThatThrownBy(() -> JacksonUtil.getJsonMapFromString(ugyldigJson))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Kunne ikke parse JSON string");
    }

    @Test
    void getJsonMapFromString_ufullstendigJson_kasterException() {
        var ufullstendigJson = "{\"navn\": \"Test\"";

        assertThatThrownBy(() -> JacksonUtil.getJsonMapFromString(ufullstendigJson))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Kunne ikke parse JSON string");
    }

    @Test
    void getJsonMapFromString_arrayPåToppnivå_kasterException() {
        var arrayJson = "[1, 2, 3]";

        assertThatThrownBy(() -> JacksonUtil.getJsonMapFromString(arrayJson))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getJsonMapFromString_norskeTegn() {
        var json = """
            {
                "søkerNavn": "Æøå Åsen",
                "beskrivelse": "Søknad om foreldrepenger"
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result)
            .containsEntry("søkerNavn", "Æøå Åsen")
            .containsEntry("beskrivelse", "Søknad om foreldrepenger");
    }

    @Test
    void getJsonMapFromString_kompleksStruktur() {
        var json = """
            {
                "felles": {
                    "søkerNavn": "Donald Duck",
                    "ytelse": "FORELDREPENGER"
                },
                "perioder": [
                    {
                        "type": "MØDREKVOTE",
                        "fom": "2026-01-01",
                        "tom": "2026-03-01",
                        "utbetalingsgrad": 100
                    }
                ],
                "behandlet": true
            }
            """;

        var result = JacksonUtil.getJsonMapFromString(json);

        assertThat(result)
            .hasSize(3)
            .containsKeys("felles", "perioder", "behandlet");

        @SuppressWarnings("unchecked")
        var felles = (Map<String, Object>) result.get("felles");
        assertThat(felles).containsEntry("søkerNavn", "Donald Duck");
    }
}
