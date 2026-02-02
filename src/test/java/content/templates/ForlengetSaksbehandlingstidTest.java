package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForlengetSaksbehandlingstidTest {
    private static final BrevMal BREVMAL = BrevMal.FORLENGET_SAKSBEHANDLINGSTID;

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_ES_FORLENGET_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_FORLENGET");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_es_FORLENGET_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_SVP_FORLENGET_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_FORLENGET");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_svp_FORLENGET_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORLENGET");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_FORLENGET");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_FORLENGET");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_død_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORLENGET_død");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORTIDLIG_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORTIDLIG");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORTIDLIG_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_KLAGE");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_KLAGE_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_død_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_KLAGE_død");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_KLAGE_død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_forlenget_saksbehandlingstid_ES_MEDLEM_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_MEDLEM");
        var expected = getExpected(BREVMAL, "forlenget-saksbehandlingstid_es_MEDLEM_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
