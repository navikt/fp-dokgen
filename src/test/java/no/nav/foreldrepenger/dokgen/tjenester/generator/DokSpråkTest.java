package no.nav.foreldrepenger.dokgen.tjenester.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DokSpråkTest {

    @Nested
    class ToStringTest {

        @Test
        void bokmålSkalReturnerNb() {
            assertThat(DokSpråk.BOKMÅL.toString()).isEqualTo("nb");
        }

        @Test
        void nynorskSkalReturnerNn() {
            assertThat(DokSpråk.NYNORSK.toString()).isEqualTo("nn");
        }

        @Test
        void engelskSkalReturnerEn() {
            assertThat(DokSpråk.ENGELSK.toString()).isEqualTo("en");
        }
    }

    @Nested
    class EnumVerdierTest {

        @Test
        void skalHaTreVerdier() {
            assertThat(DokSpråk.values()).hasSize(3);
        }

        @Test
        void skalInneholdeAlleSpråk() {
            assertThat(DokSpråk.values())
                .containsExactly(DokSpråk.BOKMÅL, DokSpråk.NYNORSK, DokSpråk.ENGELSK);
        }

        @ParameterizedTest
        @EnumSource(DokSpråk.class)
        void allaSpråkSkalHaToStringVerdi(DokSpråk språk) {
            assertThat(språk.toString())
                .isNotNull()
                .isNotBlank()
                .hasSize(2);
        }

        @ParameterizedTest
        @EnumSource(DokSpråk.class)
        void allaSpråkSkalHaGyldigSpråkkode(DokSpråk språk) {
            assertThat(språk.toString()).isIn("nb", "nn", "en");
        }
    }

    @Nested
    class ValueOfTest {

        @Test
        void skalFinneBokmålFraNavn() {
            assertThat(DokSpråk.valueOf("BOKMÅL")).isEqualTo(DokSpråk.BOKMÅL);
        }

        @Test
        void skalFinneNynorskFraNavn() {
            assertThat(DokSpråk.valueOf("NYNORSK")).isEqualTo(DokSpråk.NYNORSK);
        }

        @Test
        void skalFinneEngelskFraNavn() {
            assertThat(DokSpråk.valueOf("ENGELSK")).isEqualTo(DokSpråk.ENGELSK);
        }
    }

    @Nested
    class OrdinalTest {

        @Test
        void bokmålSkalVæreForst() {
            assertThat(DokSpråk.BOKMÅL.ordinal()).isZero();
        }

        @Test
        void nynorskSkalVæreAndre() {
            assertThat(DokSpråk.NYNORSK.ordinal()).isEqualTo(1);
        }

        @Test
        void engelskSkalVæreTredje() {
            assertThat(DokSpråk.ENGELSK.ordinal()).isEqualTo(2);
        }
    }
}
