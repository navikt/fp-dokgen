package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.jsonschema.JsonSchemaTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.pdf.PdfGeneratorTjeneste;

class DokumentGeneratorTjenesteTest {

    private JsonSchemaTjeneste jsonSchemaTjeneste;
    private HandlebarsTjeneste handlebarsTjeneste;
    private PdfGeneratorTjeneste pdfGeneratorTjeneste;
    private DokumentGeneratorTjeneste dokumentGeneratorTjeneste;

    @BeforeEach
    void setUp() {
        jsonSchemaTjeneste = mock(JsonSchemaTjeneste.class);
        handlebarsTjeneste = mock(HandlebarsTjeneste.class);
        pdfGeneratorTjeneste = mock(PdfGeneratorTjeneste.class);
        dokumentGeneratorTjeneste = new DokumentGeneratorTjeneste(jsonSchemaTjeneste, handlebarsTjeneste, pdfGeneratorTjeneste);
    }

    @Nested
    @DisplayName("createPdf")
    class CreatePdfTest {

        @Test
        @DisplayName("skal validere data mot schema før PDF-generering")
        void skalValidereDataMotSchema() {
            // Arrange
            var malNavn = "test-mal";
            var dataFelter = """
                {"testProp1": 1, "testProp2": 2}
                """;
            when(handlebarsTjeneste.genererDokumentInnhold(any(), any())).thenReturn("markdown");
            when(pdfGeneratorTjeneste.genererPdf(any())).thenReturn(new byte[]{0x25, 0x50, 0x44, 0x46});

            // Act
            dokumentGeneratorTjeneste.byggPdf(malNavn, dataFelter, DokumentSpråk.BOKMÅL, DokumentCssStyling.FOR_PDF);

            // Assert
            verify(jsonSchemaTjeneste).validerDataMotSchema(any(), any(Path.class));
        }

        @Test
        @DisplayName("skal kaste feil ved ugyldig JSON data")
        void skalKasteFeilVedUgyldigJson() {
            // Arrange
            var malNavn = "test-mal";
            var ugyldigJson = "ikke gyldig json";

            // Act & Assert
            assertThatThrownBy(() -> dokumentGeneratorTjeneste.byggPdf(malNavn, ugyldigJson, DokumentSpråk.BOKMÅL, DokumentCssStyling.FOR_PDF))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @EnumSource(DokumentSpråk.class)
        @DisplayName("skal støtte alle språk")
        void skalStøtteAlleSpråk(DokumentSpråk språk) {
            // Arrange
            var malNavn = "test-mal";
            var dataFelter = """
                {"testProp1": 1, "testProp2": 2}
                """;
            when(handlebarsTjeneste.genererDokumentInnhold(any(), any())).thenReturn("markdown");
            when(pdfGeneratorTjeneste.genererPdf(any())).thenReturn(new byte[]{0x25, 0x50, 0x44, 0x46});

            // Act & Assert - skal ikke kaste exception
            dokumentGeneratorTjeneste.byggPdf(malNavn, dataFelter, språk, DokumentCssStyling.FOR_PDF);
        }

        @ParameterizedTest
        @EnumSource(DokumentCssStyling.class)
        @DisplayName("skal støtte alle styling-typer")
        void skalStøtteAlleStylingTyper(DokumentCssStyling styling) {
            // Arrange
            var malNavn = "test-mal";
            var dataFelter = """
                {"testProp1": 1, "testProp2": 2}
                """;
            when(handlebarsTjeneste.genererDokumentInnhold(any(), any())).thenReturn("markdown");
            when(pdfGeneratorTjeneste.genererPdf(any())).thenReturn(new byte[]{0x25, 0x50, 0x44, 0x46});

            // Act & Assert - skal ikke kaste exception
            dokumentGeneratorTjeneste.byggPdf(malNavn, dataFelter, DokumentSpråk.BOKMÅL, styling);
        }
    }

    @Nested
    @DisplayName("DokSpråk enum")
    class DokumentSpråkTest {

        @Test
        @DisplayName("BOKMÅL skal returnere 'nb'")
        void bokmålSkalReturnereNb() {
            assertThat(DokumentSpråk.BOKMÅL.toString()).isEqualTo("nb");
        }

        @Test
        @DisplayName("NYNORSK skal returnere 'nn'")
        void nynorskSkalReturnereNn() {
            assertThat(DokumentSpråk.NYNORSK.toString()).isEqualTo("nn");
        }

        @Test
        @DisplayName("ENGELSK skal returnere 'en'")
        void engelskSkalReturnereEn() {
            assertThat(DokumentSpråk.ENGELSK.toString()).isEqualTo("en");
        }
    }

    @Nested
    @DisplayName("DokStyling enum")
    class DokCssCssCssStylingTest {

        @Test
        @DisplayName("FOR_PDF skal returnere 'pdf'")
        void forPdfSkalReturnerePdf() {
            assertThat(DokumentCssStyling.FOR_PDF.toString()).isEqualTo("pdf");
        }

        @Test
        @DisplayName("FOR_HTML skal returnere 'html'")
        void forHtmlSkalReturnereHtml() {
            assertThat(DokumentCssStyling.FOR_HTML.toString()).isEqualTo("html");
        }

        @Test
        @DisplayName("FOR_INNTEKTSMELDING_PDF skal returnere 'inntektsmelding_pdf'")
        void forInntektsmeldingPdfSkalReturnereInntektsmeldingPdf() {
            assertThat(DokumentCssStyling.FOR_INNTEKTSMELDING_PDF.toString()).isEqualTo("inntektsmelding_pdf");
        }
    }

    @Nested
    @DisplayName("DokMal builder")
    class DokumentMalBuilderTest {

        @Test
        @DisplayName("skal bygge DokMal med alle felter")
        void skalByggeDokMalMedAlleFelter() {
            // Arrange & Act
            var dokMal = DokumentMal.builder()
                .medNavn("test-mal")
                .medInnhold("# Overskrift\nInnhold")
                .medSpråk(DokumentSpråk.NYNORSK)
                .medStyling(DokumentCssStyling.FOR_HTML)
                .build();

            // Assert
            assertThat(dokMal)
                .satisfies(mal -> {
                    assertThat(mal.getNavn()).isEqualTo("test-mal");
                    assertThat(mal.getInnhold()).isEqualTo("# Overskrift\nInnhold");
                    assertThat(mal.getSpråk()).isEqualTo(DokumentSpråk.NYNORSK);
                    assertThat(mal.getStyling()).isEqualTo(DokumentCssStyling.FOR_HTML);
                });
        }

        @Test
        @DisplayName("skal bruke standardverdier for språk og styling")
        void skalBrukeStandardverdier() {
            // Arrange & Act
            var dokMal = DokumentMal.builder()
                .medNavn("test")
                .medInnhold("innhold")
                .build();

            // Assert
            assertThat(dokMal.getSpråk()).isEqualTo(DokumentSpråk.BOKMÅL);
            assertThat(dokMal.getStyling()).isEqualTo(DokumentCssStyling.FOR_PDF);
        }
    }
}
