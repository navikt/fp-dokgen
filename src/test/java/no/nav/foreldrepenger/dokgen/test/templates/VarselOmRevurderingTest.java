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
        compileTextAndCompareWithExpected("nb", "test_es_ANNET", "_es_ANNET_nb.txt", TEMPLATE_NAME);
    }

    private void compileTextAndCompareWithExpected(String nb, String test_es_annet, String s, String templateName) throws Exception {
        // Act
        String resultat = templateTestService.compile(templateName, TEMPLATE_PATH, nb, test_es_annet);

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(templateName, templateName + s));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_engelsk() throws Exception {
        // Act
        compileTextAndCompareWithExpected("en", "test_es_ANNET", "_es_ANNET_en.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_es_BARNIKKEREG", "_es_BARNIKKEREG_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_engelsk() throws Exception {
        // Act
        compileTextAndCompareWithExpected("en", "test_es_BARNIKKEREG", "_es_BARNIKKEREG_en.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_fp_AKTIVITET_flere_opplysninger_2barn", "_fp_AKTIVITET_flere_opplysninger_2barn_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_nynorsk() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nn", "test_fp_AKTIVITET_flere_opplysninger_2barn", "_fp_AKTIVITET_flere_opplysninger_2barn_nn.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_IKKEOPPTJENT_ikke_flere_opplysninger_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_fp_IKKEOPPTJENT_ikke_flere_opplysninger", "_fp_IKKEOPPTJENT_ikke_flere_opplysninger_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_fp_JOBBFULLTID", "_fp_JOBBFULLTID_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nn", "test_fp_JOBBFULLTID", "_fp_JOBBFULLTID_nn.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_ANNET_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_fp_ANNET", "_fp_ANNET_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_svp_JOBBFULLTID", "_svp_JOBBFULLTID_nb.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nn", "test_svp_JOBBFULLTID", "_svp_JOBBFULLTID_nn.txt", TEMPLATE_NAME);
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBB6MND_på_bokmål() throws Exception {
        // Act
        compileTextAndCompareWithExpected("nb", "test_svp_JOBB6MND", "_svp_JOBB6MND_nb.txt", TEMPLATE_NAME);
    }
}
