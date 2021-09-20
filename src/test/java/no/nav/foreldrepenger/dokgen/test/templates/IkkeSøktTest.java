package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class IkkeSøktTest {
    private static final Brevmal BREVMAL = Brevmal.IKKE_SØKT;

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "ikke-sokt_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_fp"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "ikke-sokt_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_svp"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "ikke-sokt_svp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_svp"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "ikke-sokt_svp_nn.txt"));
    }
}
