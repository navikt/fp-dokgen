package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DokumentSpråkTest {

    @Nested
    class ToStringTest {

        @Test
        void bokmålSkalReturnerNb() {
            assertThat(DokumentSpråk.BOKMÅL.toString()).isEqualTo("nb");
        }

        @Test
        void nynorskSkalReturnerNn() {
            assertThat(DokumentSpråk.NYNORSK.toString()).isEqualTo("nn");
        }

        @Test
        void engelskSkalReturnerEn() {
            assertThat(DokumentSpråk.ENGELSK.toString()).isEqualTo("en");
        }
    }

    @Nested
    class EnumVerdierTest {

        @Test
        void skalHaTreVerdier() {
            assertThat(DokumentSpråk.values()).hasSize(3);
        }

        @Test
        void skalInneholdeAlleSpråk() {
            assertThat(DokumentSpråk.values())
                .containsExactly(DokumentSpråk.BOKMÅL, DokumentSpråk.NYNORSK, DokumentSpråk.ENGELSK);
        }

        @ParameterizedTest
        @EnumSource(DokumentSpråk.class)
        void allaSpråkSkalHaToStringVerdi(DokumentSpråk språk) {
            assertThat(språk.toString())
                .isNotNull()
                .isNotBlank()
                .hasSize(2);
        }

        @ParameterizedTest
        @EnumSource(DokumentSpråk.class)
        void allaSpråkSkalHaGyldigSpråkkode(DokumentSpråk språk) {
            assertThat(språk.toString()).isIn("nb", "nn", "en");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinneBokmålFraNavn() {
            assertThat(DokumentSpråk.valueOf("BOKMÅL")).isEqualTo(DokumentSpråk.BOKMÅL);
        }

        @Test
        void skalFinneNynorskFraNavn() {
            assertThat(DokumentSpråk.valueOf("NYNORSK")).isEqualTo(DokumentSpråk.NYNORSK);
        }

        @Test
        void skalFinneEngelskFraNavn() {
            assertThat(DokumentSpråk.valueOf("ENGELSK")).isEqualTo(DokumentSpråk.ENGELSK);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void bokmålSkalVæreForst() {
            assertThat(DokumentSpråk.BOKMÅL.ordinal()).isZero();
        }

        @Test
        void nynorskSkalVæreAndre() {
            assertThat(DokumentSpråk.NYNORSK.ordinal()).isEqualTo(1);
        }

        @Test
        void engelskSkalVæreTredje() {
            assertThat(DokumentSpråk.ENGELSK.ordinal()).isEqualTo(2);
        }
    }
}
