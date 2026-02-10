package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class EtterlysInntektsmeldingTest {
    private static final BrevMal BREVMAL = BrevMal.ETTERLYS_INNTEKTSMELDING;

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_FP_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ikke_søkt_SVP_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
