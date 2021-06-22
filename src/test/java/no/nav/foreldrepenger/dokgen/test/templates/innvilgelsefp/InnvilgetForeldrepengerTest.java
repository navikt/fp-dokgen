package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerTest {

    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/template_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void førstegangsbehandling_uten_gradering_og_avslag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_automatisk_ingen_gradering_ingen_avslag");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_template_forstegangsbehandling_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_avslag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_med_avslag");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_template_forstegangsbehandling_med_avslag_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_fritekst_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_med_fritekst");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_template_forstegangsbehandling_med_fritekst_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_gradering_flere_arbgivere_og_gradering");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME,  TEMPLATE_NAME + "_førstegangsbehandling_gradering_nb.txt"));
    }
}
