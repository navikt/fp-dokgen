package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class InnhenteOpplysningerTest {
    private static final String TEMPLATE_NAME = "innhente-opplysninger";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_engelsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_fgb_død");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_fgb_død");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_revurdering");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_revurdering");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_klage");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_KA() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage_ka");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_ka.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage_død");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_klage_død");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp_fgb");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_endringssøknad");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_endringssøknad");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_med_utkast() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_fgb_utkast");

        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_utkast.txt"));
    }
}
