package no.nav.foreldrepenger.dokgen.tjenester.v1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentCssStyling;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentSpråk;

class DokumentGeneratorTjenesteMapperTest {

    @Test
    void skal_mappe_CssStyling_PDF_til_DokumentCssStyling_FOR_PDF() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokCssStyling(DokumentGeneratorRequest.CssStyling.PDF);

        assertThat(resultat).isEqualTo(DokumentCssStyling.FOR_PDF);
    }

    @Test
    void skal_mappe_CssStyling_HTML_til_DokumentCssStyling_FOR_HTML() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokCssStyling(DokumentGeneratorRequest.CssStyling.HTML);

        assertThat(resultat).isEqualTo(DokumentCssStyling.FOR_HTML);
    }

    @Test
    void skal_mappe_CssStyling_INNTEKTSMELDING_PDF_til_DokumentCssStyling_FOR_INNTEKTSMELDING_PDF() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokCssStyling(DokumentGeneratorRequest.CssStyling.INNTEKTSMELDING_PDF);

        assertThat(resultat).isEqualTo(DokumentCssStyling.FOR_INNTEKTSMELDING_PDF);
    }

    @Test
    void skal_mappe_Språk_BOKMÅL_til_DokumentSpråk_BOKMÅL() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokSpråk(DokumentGeneratorRequest.Språk.BOKMÅL);

        assertThat(resultat).isEqualTo(DokumentSpråk.BOKMÅL);
    }

    @Test
    void skal_mappe_Språk_NYNORSK_til_DokumentSpråk_NYNORSK() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokSpråk(DokumentGeneratorRequest.Språk.NYNORSK);

        assertThat(resultat).isEqualTo(DokumentSpråk.NYNORSK);
    }

    @Test
    void skal_mappe_Språk_ENGELSK_til_DokumentSpråk_ENGELSK() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokSpråk(DokumentGeneratorRequest.Språk.ENGELSK);

        assertThat(resultat).isEqualTo(DokumentSpråk.ENGELSK);
    }

    @Test
    void skal_returnere_null_når_Språk_er_null() {
        var resultat = DokumentGeneratorTjenesteMapper.mapTilDokSpråk(null);

        assertThat(resultat).isNull();
    }
}
