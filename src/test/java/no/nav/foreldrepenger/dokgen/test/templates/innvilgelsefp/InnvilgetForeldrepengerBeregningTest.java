package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class InnvilgetForeldrepengerBeregningTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/beregning_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void undermal_utbetaling_en_ag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_beregning_enn_ag");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_beregning_enn_ag_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_to_ag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_beregning_to_ag");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_beregning_to_ag_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_næring_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_beregning_sn");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_beregning_sn_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_frilans_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "førstegangsbehandling_beregning_frilans");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_beregning_frilans_nb.txt"));
    }
}
