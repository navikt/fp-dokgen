package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DokCssCssCssStylingTest {

    @Nested
    class ToStringTest {

        @Test
        void pdfSkalReturnePdf() {
            assertThat(DokumentCssStyling.FOR_PDF).hasToString("pdf");
        }

        @Test
        void htmlSkalReturneHtml() {
            assertThat(DokumentCssStyling.FOR_HTML).hasToString("html");
        }

        @Test
        void pdfInntektsmeldingSkalReturnePdfinntektsmelding() {
            assertThat(DokumentCssStyling.FOR_INNTEKTSMELDING_PDF).hasToString("inntektsmelding_pdf");
        }
    }

    @Nested
    class EnumVerdierTest {

        @Test
        void skalHaTreVerdier() {
            assertThat(DokumentCssStyling.values()).hasSize(3);
        }

        @Test
        void skalInneholdeAlleStylingTyper() {
            assertThat(DokumentCssStyling.values())
                .containsExactly(DokumentCssStyling.FOR_PDF, DokumentCssStyling.FOR_HTML, DokumentCssStyling.FOR_INNTEKTSMELDING_PDF);
        }

        @ParameterizedTest
        @EnumSource(DokumentCssStyling.class)
        void alleStylingTyperSkalHaToStringVerdi(DokumentCssStyling styling) {
            assertThat(styling.toString())
                .isNotNull()
                .isNotBlank()
                .isLowerCase();
        }

        @ParameterizedTest
        @EnumSource(DokumentCssStyling.class)
        void alleStylingTyperSkalHaGyldigVerdi(DokumentCssStyling styling) {
            assertThat(styling.toString()).isIn("pdf", "html", "inntektsmelding_pdf");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinnePdfFraNavn() {
            assertThat(DokumentCssStyling.valueOf("FOR_PDF")).isEqualTo(DokumentCssStyling.FOR_PDF);
        }

        @Test
        void skalFinneHtmlFraNavn() {
            assertThat(DokumentCssStyling.valueOf("FOR_HTML")).isEqualTo(DokumentCssStyling.FOR_HTML);
        }

        @Test
        void skalFinnePdfInntektsmeldingFraNavn() {
            assertThat(DokumentCssStyling.valueOf("FOR_INNTEKTSMELDING_PDF")).isEqualTo(DokumentCssStyling.FOR_INNTEKTSMELDING_PDF);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void pdfSkalVaereForst() {
            assertThat(DokumentCssStyling.FOR_PDF.ordinal()).isZero();
        }

        @Test
        void htmlSkalVaereAndre() {
            assertThat(DokumentCssStyling.FOR_HTML.ordinal()).isEqualTo(1);
        }

        @Test
        void pdfInntektsmeldingSkalVaereTredje() {
            assertThat(DokumentCssStyling.FOR_INNTEKTSMELDING_PDF.ordinal()).isEqualTo(2);
        }
    }

    @Nested
    class ToStringErSmallLettersTest {

        @ParameterizedTest
        @EnumSource(DokumentCssStyling.class)
        void toStringSkalVaereSmaBokstaver(DokumentCssStyling styling) {
            var result = styling.toString();
            assertThat(result).isEqualTo(result.toLowerCase());
        }

        @ParameterizedTest
        @EnumSource(DokumentCssStyling.class)
        void toStringSkalIkkeInneholdeUnderscore(DokumentCssStyling styling) {
            assertThat(styling.toString()).doesNotContain("for_");
        }
    }
}
