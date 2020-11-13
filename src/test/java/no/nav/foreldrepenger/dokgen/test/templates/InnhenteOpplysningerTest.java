package no.nav.foreldrepenger.dokgen.test.templates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class InnhenteOpplysningerTest {
    private static final String TEMPLATE_NAME = "innhente-opplysninger";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_es_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_es_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_es_fgb_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_es_fgb_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_es_revurdering");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_es_revurdering");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_es_klage");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_es_klage");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_es_klage_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_es_klage_død");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_fp_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_fp_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_svp_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_svp_fgb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_fp_endringssøknad");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_fp_endringssøknad");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nn.txt"));
    }
}
