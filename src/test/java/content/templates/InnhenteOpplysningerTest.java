package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class InnhenteOpplysningerTest {
    private static final BrevMal BREVMAL = BrevMal.INNHENTE_OPPLYSNINGER;

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_es_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_es_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb_død");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_es_fgb_død");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_død_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_revurdering");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_revurdering_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_es_revurdering");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_revurdering_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_klage");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_klage_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_es_klage");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_klage_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_klage_død");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_klage_død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_es_klage_død");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_klage_død_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_fp_fgb_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_fp_fgb_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_svp_fgb_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_svp_fgb");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_svp_fgb_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_endringssøknad");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_fp_endringssøknad_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_endringssøknad");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_fp_endringssøknad_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innhente_opplysninger_med_utkast() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb_utkast");
        var expected = getExpected(BREVMAL, "innhente-opplysninger_es_fgb_utkast.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
