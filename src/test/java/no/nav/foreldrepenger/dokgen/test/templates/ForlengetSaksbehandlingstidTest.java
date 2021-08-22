package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class ForlengetSaksbehandlingstidTest {
    private static final String TEMPLATE_NAME = "forlenget-saksbehandlingstid";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_ES_FORLENGET_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es_FORLENGET");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_FORLENGET_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_SVP_FORLENGET_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp_FORLENGET");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_FORLENGET_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORLENGET");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp_FORLENGET");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_på_engelsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_fp_FORLENGET");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORLENGET_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORLENGET_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORLENGET_død_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_FORTIDLIG_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_FORTIDLIG");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_FORTIDLIG_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_KLAGE");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_KLAGE_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_KLAGE_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_KLAGE_død");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_KLAGE_død_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_forlenget_saksbehandlingstid_FP_MEDLEM_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp_MEDLEM");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_MEDLEM_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}
