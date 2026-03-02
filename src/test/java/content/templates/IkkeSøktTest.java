package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class IkkeSøktTest {
    private static final BrevMal BREVMAL = BrevMal.IKKE_SØKT;

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp");
        var expected = getExpected(BREVMAL, "ikke-sokt_fp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp");
        var expected = getExpected(BREVMAL, "ikke-sokt_fp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_bokmål_for_engelsk_også() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp");
        var expected = getExpected(BREVMAL, "ikke-sokt_fp_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp");
        var expected = getExpected(BREVMAL, "ikke-sokt_svp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_svp");
        var expected = getExpected(BREVMAL, "ikke-sokt_svp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
