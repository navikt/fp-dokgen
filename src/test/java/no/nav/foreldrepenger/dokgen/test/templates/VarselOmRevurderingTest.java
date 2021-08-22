package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VarselOmRevurderingTest {
    private static final String TEMPLATE_NAME = "varsel-revurdering";
    private static final String TEMPLATE_PATH = "/template_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_ANNET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_ANNET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_engelsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_ANNET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_ANNET_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_BARNIKKEREG");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_BARNIKKEREG_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_engelsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_BARNIKKEREG");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_BARNIKKEREG_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_AKTIVITET_flere_opplysninger_2barn");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_AKTIVITET_flere_opplysninger_2barn_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_AKTIVITET_flere_opplysninger_2barn");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_AKTIVITET_flere_opplysninger_2barn_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_IKKEOPPTJENT_ikke_flere_opplysninger_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_IKKEOPPTJENT_ikke_flere_opplysninger");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_IKKEOPPTJENT_ikke_flere_opplysninger_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_JOBBFULLTID");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_JOBBFULLTID_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_JOBBFULLTID");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_JOBBFULLTID_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_ANNET_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_ANNET");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_ANNET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_JOBBFULLTID");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBBFULLTID_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp_JOBBFULLTID");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBBFULLTID_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBB6MND_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_JOBB6MND");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBB6MND_nb.txt"));
    }
}
