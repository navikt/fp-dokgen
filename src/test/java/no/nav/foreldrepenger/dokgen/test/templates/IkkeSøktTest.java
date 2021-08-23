package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class IkkeSøktTest {
    private static final String TEMPLATE_NAME = "ikke-sokt";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nn.txt"));
    }
}
