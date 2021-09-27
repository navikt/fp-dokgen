package no.nav.foreldrepenger.dokgen.test.templates;


import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class IngenEndringTest {
    private static final Brevmal BREVMAL = Brevmal.INGEN_ENDRING;

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "ingen-endring_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_fp"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "ingen-endring_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "ingen-endring_es_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_engelsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "test_es"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "ingen-endring_es_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_SVP_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_svp"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "ingen-endring_svp_nb.txt"));
    }
}
