package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerUtbetalingTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/utbetaling_";
    private static final TemplateTestService templateTestService = new TemplateTestService();


    @Test
    public void undermal_utbetaling_ingen_refusjon_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_ingen_refusjon");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_utbetaling_ingen_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_full_refusjon_rettigheter_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_full_refusjon");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_utbetaling_full_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_delvis_refusjon_rettigheter_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_delvis_refusjon");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_utbetaling_delvis_refusjon_nb.txt"));
    }


}