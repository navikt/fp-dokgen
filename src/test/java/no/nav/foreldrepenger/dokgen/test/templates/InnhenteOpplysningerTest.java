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

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_engelsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_fgb_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_fgb_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_død_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_revurdering");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_revurdering");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_revurdering_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_klage");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_KA() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage_ka");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_ka.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_klage_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_es_klage_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_klage_død_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_fgb_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_svp_fgb");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_fgb_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_endringssøknad");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_endringssøknad");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_endringssøknad_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_med_utkast() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_fgb_utkast");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_fgb_utkast.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}
