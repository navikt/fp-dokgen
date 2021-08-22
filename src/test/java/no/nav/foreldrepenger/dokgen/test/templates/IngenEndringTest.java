package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IngenEndringTest {
    private static final String TEMPLATE_NAME = "ingen-endring";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_nynorsk() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_engelsk() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_SVP_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nb.txt"));
    }
}
