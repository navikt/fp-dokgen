package no.nav.foreldrepenger.dokgen.test.templates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class IkkeSøktTest {
    private static final String TEMPLATE_NAME = "ikke-sokt";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_FP_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_svp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ikke_søkt_SVP_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_svp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nn.txt"));
    }
}
