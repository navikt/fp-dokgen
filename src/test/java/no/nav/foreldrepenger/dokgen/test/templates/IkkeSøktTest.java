package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IkkeSøktTest {
    private static final String TEMPLATE_NAME = "ikke-sokt";
    private static final String TEMPLATE_PATH = "/template_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nn.txt"));
    }
}
