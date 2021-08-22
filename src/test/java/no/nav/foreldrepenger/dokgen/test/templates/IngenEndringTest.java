package no.nav.foreldrepenger.dokgen.test.templates;


import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class IngenEndringTest {
    private static final String TEMPLATE_NAME = "ingen-endring";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_fp");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_FP_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_fp");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_fp_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_es");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_ES_på_engelsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_es");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_es_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_ingen_endring_SVP_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_svp");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_svp_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}
