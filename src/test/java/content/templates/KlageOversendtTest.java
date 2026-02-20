package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class KlageOversendtTest {

    private static final BrevMal BREVMAL = BrevMal.KLAGE_OVERSENDT;

    @Test
    void klage_oversendt_fp_tilbakekreving_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_fp_tilbakekreving_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_fp_tilbakekreving_en_på_nb() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_svp_ikke_tilbakekreving_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_ikke-tilbakekreving");
        var expected = getExpected(BREVMAL, "svp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
