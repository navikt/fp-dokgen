package content.templates;


import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class IngenEndringTest {
    private static final BrevMal BREVMAL = BrevMal.INGEN_ENDRING;

    @Test
    void skal_generere_brev_for_ingen_endring_FP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp");
        var expected = getExpected(BREVMAL, "ingen-endring_fp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_FP_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp");
        var expected = getExpected(BREVMAL, "ingen-endring_fp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_ES_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es");
        var expected = getExpected(BREVMAL, "ingen-endring_es_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_ES_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_es");
        var expected = getExpected(BREVMAL, "ingen-endring_es_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_SVP_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp");
        var expected = getExpected(BREVMAL, "ingen-endring_svp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
