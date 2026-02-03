package no.nav.foreldrepenger.dokgen.tjenester.generator;

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
            assertThat(DokCssStyling.FOR_PDF).hasToString("pdf");
        }

        @Test
        void htmlSkalReturneHtml() {
            assertThat(DokCssStyling.FOR_HTML).hasToString("html");
        }

        @Test
        void pdfInntektsmeldingSkalReturnePdfinntektsmelding() {
            assertThat(DokCssStyling.FOR_INNTEKTSMELDING_PDF).hasToString("inntektsmelding_pdf");
        }
    }

    @Nested
    class EnumVerdierTest {

        @Test
        void skalHaTreVerdier() {
            assertThat(DokCssStyling.values()).hasSize(3);
        }

        @Test
        void skalInneholdeAlleStylingTyper() {
            assertThat(DokCssStyling.values())
                .containsExactly(DokCssStyling.FOR_PDF, DokCssStyling.FOR_HTML, DokCssStyling.FOR_INNTEKTSMELDING_PDF);
        }

        @ParameterizedTest
        @EnumSource(DokCssStyling.class)
        void alleStylingTyperSkalHaToStringVerdi(DokCssStyling styling) {
            assertThat(styling.toString())
                .isNotNull()
                .isNotBlank()
                .isLowerCase();
        }

        @ParameterizedTest
        @EnumSource(DokCssStyling.class)
        void alleStylingTyperSkalHaGyldigVerdi(DokCssStyling styling) {
            assertThat(styling.toString()).isIn("pdf", "html", "inntektsmelding_pdf");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinnePdfFraNavn() {
            assertThat(DokCssStyling.valueOf("FOR_PDF")).isEqualTo(DokCssStyling.FOR_PDF);
        }

        @Test
        void skalFinneHtmlFraNavn() {
            assertThat(DokCssStyling.valueOf("FOR_HTML")).isEqualTo(DokCssStyling.FOR_HTML);
        }

        @Test
        void skalFinnePdfInntektsmeldingFraNavn() {
            assertThat(DokCssStyling.valueOf("FOR_INNTEKTSMELDING_PDF")).isEqualTo(DokCssStyling.FOR_INNTEKTSMELDING_PDF);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void pdfSkalVaereForst() {
            assertThat(DokCssStyling.FOR_PDF.ordinal()).isZero();
        }

        @Test
        void htmlSkalVaereAndre() {
            assertThat(DokCssStyling.FOR_HTML.ordinal()).isEqualTo(1);
        }

        @Test
        void pdfInntektsmeldingSkalVaereTredje() {
            assertThat(DokCssStyling.FOR_INNTEKTSMELDING_PDF.ordinal()).isEqualTo(2);
        }
    }

    @Nested
    class ToStringErSmallLettersTest {

        @ParameterizedTest
        @EnumSource(DokCssStyling.class)
        void toStringSkalVaereSmaBokstaver(DokCssStyling styling) {
            var result = styling.toString();
            assertThat(result).isEqualTo(result.toLowerCase());
        }

        @ParameterizedTest
        @EnumSource(DokCssStyling.class)
        void toStringSkalIkkeInneholdeUnderscore(DokCssStyling styling) {
            assertThat(styling.toString()).doesNotContain("for_");
        }
    }
}
