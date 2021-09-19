package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class ForlengetSaksbehandlingstidTest {
    private static final Brevmal BREVMAL = Brevmal.FORLENGET_SAKSBEHANDLINGSTID;

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_ES_FORLENGET_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_FORLENGET"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_es_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_SVP_FORLENGET_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_FORLENGET"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_svp_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORLENGET"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_fp_FORLENGET"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_engelsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "test_fp_FORLENGET"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_død_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORLENGET_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORLENGET_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORTIDLIG_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_FORTIDLIG"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_FORTIDLIG_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_KLAGE"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_KLAGE_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_død_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_KLAGE_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_KLAGE_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_MEDLEM_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_MEDLEM"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forlenget-saksbehandlingstid_fp_MEDLEM_nb.txt"));
    }
}
