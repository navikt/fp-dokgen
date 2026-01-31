package no.nav.foreldrepenger.dokgen.tjenester.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

import no.nav.foreldrepenger.dokgen.tjenester.handlebars.HandlebarsTjeneste;
import no.nav.vedtak.mapper.json.DefaultJsonMapper;

import org.junit.jupiter.api.Test;

class DokumentDokMalTjenesteTest {

    @Test
    void compilerTemplateOK() {
        // Arrange
        var templateResource = DokMal.builder()
            .medNavn("test-mal")
            .medInnhold("Dette er en enkel addisjonstest-mal {{testProp1}} + {{testProp2}} = {{resultat}}.")
            .build();

        Map<String, Object> jsonInput = Map.of("testProp1", 5, "testProp2", 3, "resultat", "8");

        // Act
        var tjeneste = new DokGeneratorTjeneste(null, new HandlebarsTjeneste(), null);
        var resultat = tjeneste.kombinerMalMedData(templateResource, jsonInput);

        // Assert
        assertEquals("Dette er en enkel addisjonstest-mal 5 + 3 = 8.", resultat);
    }

    @Test
    void compilerTemplate_med_flere_nivåer_json() throws IOException {
        // Arrange
        var templateResource = DokMal.builder()
            .medNavn("test-mal-to")
            .medInnhold("Dette er en enkel addisjonstest-mal for {{felles.søkerNavn}} {{felles.søkerEtternavn}}: {{testProp1}} + {{testProp2}} = {{resultat}}.")
            .build();

        var jsonString = """
        {
            "testProp1": 5,
            "testProp2": 3,
            "resultat": "8",
            "felles": {
                "søkerNavn": "Donald",
                "søkerEtternavn": "Duck"
            }
        }
        """;

        Map<String, Object> jsonInput = DefaultJsonMapper.getJsonMapper().readValue(jsonString, Map.class);

        // Act
        var dokGeneratorTjeneste = new DokGeneratorTjeneste(null, new HandlebarsTjeneste(), null);
        var resultat = dokGeneratorTjeneste.kombinerMalMedData(templateResource, jsonInput);

        // Assert
        assertEquals("Dette er en enkel addisjonstest-mal for Donald Duck: 5 + 3 = 8.", resultat);
    }
}
