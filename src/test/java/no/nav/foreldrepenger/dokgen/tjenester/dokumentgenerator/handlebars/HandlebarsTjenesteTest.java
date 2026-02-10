package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.handlebars;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentCssStyling;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentSpråk;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.utils.JacksonUtil;

class HandlebarsTjenesteTest {

    private final HandlebarsTjeneste handlebarsTjeneste = new HandlebarsTjeneste();

    @Test
    void compilerTemplateOK() {
        // Arrange
        var malInnhold = "Dette er en enkel addisjonstest-mal {{testProp1}} + {{testProp2}} = {{resultat}}.";
        Map<String, Object> jsonInput = Map.of("testProp1", 5, "testProp2", 3, "resultat", "8");

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).isEqualTo("Dette er en enkel addisjonstest-mal 5 + 3 = 8.");
    }

    @Test
    void compilerTemplate_med_flere_nivåer_json() throws IOException {
        // Arrange
        var malInnhold = "Dette er en enkel addisjonstest-mal for {{felles.søkerNavn}} {{felles.søkerEtternavn}}: {{testProp1}} + {{testProp2}} = {{resultat}}.";

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

        Map<String, Object> jsonInput = JacksonUtil.JSON_MAPPER.readValue(jsonString, Map.class);

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).isEqualTo("Dette er en enkel addisjonstest-mal for Donald Duck: 5 + 3 = 8.");
    }

    @Test
    void skalHåndtereNorskeTegn() {
        // Arrange
        var malInnhold = "Hei {{navn}}, du har fått innvilget {{beløp}} kroner i stønad.";
        Map<String, Object> jsonInput = Map.of("navn", "Donald Duck", "beløp", "15000");

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).contains("Donald Duck").contains("15000 kroner");
    }

    @Test
    void skalHåndtereListeData() {
        // Arrange
        var malInnhold = "Perioder:{{#each perioder}} {{this}}{{/each}}";
        Map<String, Object> jsonInput = Map.of("perioder", List.of("jan", "feb", "mar"));

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).isEqualTo("Perioder: jan feb mar");
    }

    @Test
    void skalHåndtereBetingetInnhold() {
        // Arrange
        var malInnhold = "{{#if erInnvilget}}Søknaden er innvilget{{else}}Søknaden er avslått{{/if}}";
        Map<String, Object> innvilgetData = Map.of("erInnvilget", true);
        Map<String, Object> avslåttData = Map.of("erInnvilget", false);

        // Act
        var innvilgetResultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, innvilgetData);
        var avslåttResultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, avslåttData);

        // Assert
        assertThat(innvilgetResultat).isEqualTo("Søknaden er innvilget");
        assertThat(avslåttResultat).isEqualTo("Søknaden er avslått");
    }

    @ParameterizedTest
    @EnumSource(DokumentSpråk.class)
    void skalStøtteAlleSpråk(DokumentSpråk språk) {
        // Assert
        assertThat(språk.toString()).isIn("nb", "nn", "en");
    }

    @ParameterizedTest
    @EnumSource(DokumentCssStyling.class)
    void skalStøtteAlleStylingTyper(DokumentCssStyling styling) {
        // Assert
        assertThat(styling.toString()).isIn("pdf", "html", "inntektsmelding_pdf");
    }
}
