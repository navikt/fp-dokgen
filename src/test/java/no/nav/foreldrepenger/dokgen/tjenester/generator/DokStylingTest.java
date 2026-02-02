package no.nav.foreldrepenger.dokgen.tjenester.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DokStylingTest {

    @Nested
    class ToStringTest {

        @Test
        void pdfSkalReturnePdf() {
            assertThat(DokStyling.FOR_PDF).hasToString("pdf");
        }

        @Test
        void htmlSkalReturneHtml() {
            assertThat(DokStyling.FOR_HTML).hasToString("html");
        }

        @Test
        void pdfInntektsmeldingSkalReturnePdfinntektsmelding() {
            assertThat(DokStyling.FOR_INNTEKTSMELDING_PDF).hasToString("inntektsmelding_pdf");
        }
    }

    @Nested
    class EnumVerdierTest {

        @Test
        void skalHaTreVerdier() {
            assertThat(DokStyling.values()).hasSize(3);
        }

        @Test
        void skalInneholdeAlleStylingTyper() {
            assertThat(DokStyling.values())
                .containsExactly(DokStyling.FOR_PDF, DokStyling.FOR_HTML, DokStyling.FOR_INNTEKTSMELDING_PDF);
        }

        @ParameterizedTest
        @EnumSource(DokStyling.class)
        void alleStylingTyperSkalHaToStringVerdi(DokStyling styling) {
            assertThat(styling.toString())
                .isNotNull()
                .isNotBlank()
                .isLowerCase();
        }

        @ParameterizedTest
        @EnumSource(DokStyling.class)
        void alleStylingTyperSkalHaGyldigVerdi(DokStyling styling) {
            assertThat(styling.toString()).isIn("pdf", "html", "inntektsmelding_pdf");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinnePdfFraNavn() {
            assertThat(DokStyling.valueOf("FOR_PDF")).isEqualTo(DokStyling.FOR_PDF);
        }

        @Test
        void skalFinneHtmlFraNavn() {
            assertThat(DokStyling.valueOf("FOR_HTML")).isEqualTo(DokStyling.FOR_HTML);
        }

        @Test
        void skalFinnePdfInntektsmeldingFraNavn() {
            assertThat(DokStyling.valueOf("FOR_INNTEKTSMELDING_PDF")).isEqualTo(DokStyling.FOR_INNTEKTSMELDING_PDF);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void pdfSkalVaereForst() {
            assertThat(DokStyling.FOR_PDF.ordinal()).isZero();
        }

        @Test
        void htmlSkalVaereAndre() {
            assertThat(DokStyling.FOR_HTML.ordinal()).isEqualTo(1);
        }

        @Test
        void pdfInntektsmeldingSkalVaereTredje() {
            assertThat(DokStyling.FOR_INNTEKTSMELDING_PDF.ordinal()).isEqualTo(2);
        }
    }

    @Nested
    class ToStringErSmallLettersTest {

        @ParameterizedTest
        @EnumSource(DokStyling.class)
        void toStringSkalVaereSmaBokstaver(DokStyling styling) {
            var result = styling.toString();
            assertThat(result).isEqualTo(result.toLowerCase());
        }

        @ParameterizedTest
        @EnumSource(DokStyling.class)
        void toStringSkalIkkeInneholdeUnderscore(DokStyling styling) {
            assertThat(styling.toString()).doesNotContain("for_");
        }
    }
}
