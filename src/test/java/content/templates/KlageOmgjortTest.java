package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import content.support.Språk;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;

class KlageOmgjortTest {

    private static final BrevMal BREVMAL = BrevMal.KLAGE_OMGJORT;

    @Test
    void klage_omgjort_fp_ka_tilbakekreving_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_nfp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_omgjort_fp_ka_tilbakekreving_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_nfp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_omgjort_fp_ka_tilbakekreving_en_på_nb() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_nfp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_omgjort_es_nfp_tilbakekreving_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_nfp_tilbakekreving");
        var expected = getExpected(BREVMAL, "es_nfp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_omgjort_svp_nfp_ikke_tilbakekreving_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_nfp_ikke-tilbakekreving");
        var expected = getExpected(BREVMAL, "svp_nfp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
