package no.nav.foreldrepenger.dokgen.test.templates;


import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class IngenEndringTest {
    private static final Brevmal BREVMAL = Brevmal.INGEN_ENDRING;

    @Test
    void skal_generere_brev_for_ingen_endring_FP_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp");
        var expected = getExpected(BREVMAL, "ingen-endring_fp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_FP_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp");
        var expected = getExpected(BREVMAL, "ingen-endring_fp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_ES_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es");
        var expected = getExpected(BREVMAL, "ingen-endring_es_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_ES_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_es");
        var expected = getExpected(BREVMAL, "ingen-endring_es_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_ingen_endring_SVP_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp");
        var expected = getExpected(BREVMAL, "ingen-endring_svp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
