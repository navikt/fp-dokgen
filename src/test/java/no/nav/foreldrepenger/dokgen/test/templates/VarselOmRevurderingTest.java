package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class VarselOmRevurderingTest {
    private static final String TEMPLATE_NAME = "varsel-revurdering";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_ANNET"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_es_ANNET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_engelsk() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_ANNET"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_es_ANNET_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_BARNIKKEREG"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_es_BARNIKKEREG_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_engelsk() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_BARNIKKEREG"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_es_BARNIKKEREG_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_AKTIVITET_flere_opplysninger_2barn"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_AKTIVITET_flere_opplysninger_2barn_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_nynorsk() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_AKTIVITET_flere_opplysninger_2barn"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_AKTIVITET_flere_opplysninger_2barn_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_IKKEOPPTJENT_ikke_flere_opplysninger_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_IKKEOPPTJENT_ikke_flere_opplysninger"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_IKKEOPPTJENT_ikke_flere_opplysninger_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_JOBBFULLTID"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_JOBBFULLTID_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_JOBBFULLTID"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_JOBBFULLTID_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_FP_kode_ANNET_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_ANNET"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_ANNET_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_JOBBFULLTID"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBBFULLTID_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_nynorsk() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp_JOBBFULLTID"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBBFULLTID_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBB6MND_på_bokmål() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_JOBB6MND"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_JOBB6MND_nb.txt"));
    }
}
