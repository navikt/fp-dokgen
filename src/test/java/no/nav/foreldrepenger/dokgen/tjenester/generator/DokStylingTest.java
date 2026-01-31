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
            assertThat(DokStyling.PDF).hasToString("pdf");
        }

        @Test
        void htmlSkalReturneHtml() {
            assertThat(DokStyling.HTML).hasToString("html");
        }

        @Test
        void pdfInntektsmeldingSkalReturnePdfinntektsmelding() {
            assertThat(DokStyling.PDFINNTEKTSMELDING).hasToString("pdfinntektsmelding");
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
                .containsExactly(DokStyling.PDF, DokStyling.HTML, DokStyling.PDFINNTEKTSMELDING);
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
            assertThat(styling.toString()).isIn("pdf", "html", "pdfinntektsmelding");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinnePdfFraNavn() {
            assertThat(DokStyling.valueOf("PDF")).isEqualTo(DokStyling.PDF);
        }

        @Test
        void skalFinneHtmlFraNavn() {
            assertThat(DokStyling.valueOf("HTML")).isEqualTo(DokStyling.HTML);
        }

        @Test
        void skalFinnePdfInntektsmeldingFraNavn() {
            assertThat(DokStyling.valueOf("PDFINNTEKTSMELDING")).isEqualTo(DokStyling.PDFINNTEKTSMELDING);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void pdfSkalVaereForst() {
            assertThat(DokStyling.PDF.ordinal()).isZero();
        }

        @Test
        void htmlSkalVaereAndre() {
            assertThat(DokStyling.HTML.ordinal()).isEqualTo(1);
        }

        @Test
        void pdfInntektsmeldingSkalVaereTredje() {
            assertThat(DokStyling.PDFINNTEKTSMELDING.ordinal()).isEqualTo(2);
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
            assertThat(styling.toString()).doesNotContain("_");
        }
    }
}
