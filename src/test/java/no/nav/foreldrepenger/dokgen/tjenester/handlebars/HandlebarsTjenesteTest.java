package no.nav.foreldrepenger.dokgen.tjenester.handlebars;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import no.nav.foreldrepenger.dokgen.tjenester.generator.DokMal;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokSpråk;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;
import no.nav.vedtak.mapper.json.DefaultJsonMapper;

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

        Map<String, Object> jsonInput = DefaultJsonMapper.getJsonMapper().readValue(jsonString, Map.class);

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).isEqualTo("Dette er en enkel addisjonstest-mal for Donald Duck: 5 + 3 = 8.");
    }

    @Test
    void skalHåndtereNorskeTegn() {
        // Arrange
        var malInnhold = "Hei {{navn}}, du har fått innvilget {{beløp}} kroner i stønad.";
        Map<String, Object> jsonInput = Map.of("navn", "Åse Ødegård", "beløp", "15000");

        // Act
        var resultat = handlebarsTjeneste.genererDokumentInnhold(malInnhold, jsonInput);

        // Assert
        assertThat(resultat).contains("Åse Ødegård").contains("15000 kroner");
    }

    @Test
    void skalHåndtereListeData() {
        // Arrange
        var malInnhold = "Perioder:{{#each perioder}} {{this}}{{/each}}";
        Map<String, Object> jsonInput = Map.of("perioder", java.util.List.of("jan", "feb", "mar"));

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
    @EnumSource(DokSpråk.class)
    void skalStøtteAlleSpråk(DokSpråk språk) {
        // Assert
        assertThat(språk.toString()).isIn("nb", "nn", "en");
    }

    @ParameterizedTest
    @EnumSource(DokStyling.class)
    void skalStøtteAlleStylingTyper(DokStyling styling) {
        // Assert
        assertThat(styling.toString()).isIn("pdf", "html", "inntektsmelding_pdf");
    }

    @Test
    void skalByggeGyldigDokMal() {
        // Arrange & Act
        var dokMal = DokMal.builder().medNavn("test-mal").medInnhold("Innhold").medSpråk(DokSpråk.NYNORSK).medStyling(DokStyling.FOR_PDF).build();

        // Assert
        assertThat(dokMal.getNavn()).isEqualTo("test-mal");
        assertThat(dokMal.getInnhold()).isEqualTo("Innhold");
        assertThat(dokMal.getSpråk()).isEqualTo(DokSpråk.NYNORSK);
        assertThat(dokMal.getStyling()).isEqualTo(DokStyling.FOR_PDF);
    }

    @Test
    void skalBrukStandardverdierForDokMal() {
        // Arrange & Act
        var dokMal = DokMal.builder().medNavn("test").medInnhold("test innhold").build();

        // Assert
        assertThat(dokMal.getSpråk()).isEqualTo(DokSpråk.BOKMÅL);
        assertThat(dokMal.getStyling()).isEqualTo(DokStyling.FOR_PDF);
    }
}
