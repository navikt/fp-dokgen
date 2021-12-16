package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class EtterlysInntektsmeldingTest {
    private static final Brevmal BREVMAL = Brevmal.ETTERLYS_INNTEKTSMELDING;

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp");
        var expected = getExpected(BREVMAL, "etterlys_im_fp_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_svp");
        var expected = getExpected(BREVMAL, "etterlys_im_svp_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
