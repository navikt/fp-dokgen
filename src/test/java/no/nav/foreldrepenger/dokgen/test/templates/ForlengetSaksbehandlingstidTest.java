package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ForlengetSaksbehandlingstidTest {
    private static final String TEMPLATE_NAME = "forlenget-saksbehandlingstid";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_ES_FORLENGET_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_FORLENGET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_SVP_FORLENGET_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_FORLENGET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORLENGET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_nynorsk() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_FORLENGET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_engelsk() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_fp_FORLENGET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_død_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORLENGET_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORTIDLIG_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORTIDLIG");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORTIDLIG_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_KLAGE");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_KLAGE_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_død_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_KLAGE_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_KLAGE_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_MEDLEM_på_bokmål() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_MEDLEM");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_MEDLEM_nb.txt"));
    }
}
