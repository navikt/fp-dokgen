package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class InnvilgetForeldrepengerBeregningTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/beregning_";
    private static final String EXPECTED_PREFIX = "beregning/innvilget-foreldrepenger_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void undermal_beregning_en_ag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_en_ag");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "en_ag_nb.txt"));
    }

    @Test
    public void undermal_beregning_to_ag_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_to_ag");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "to_ag_nb.txt"));
    }

    @Test
    public void undermal_beregning_næring_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_næring");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "næring_nb.txt"));
    }

    @Test
    public void undermal_beregning_frilans_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_frilans");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "frilans_nb.txt"));
    }

    @Test
    public void undermal_beregning_fri_ytelse_80_dg_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/revurdering_kun_ytelse_80_dg");
        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "kun_ytelse_nb.txt"));
    }
}
