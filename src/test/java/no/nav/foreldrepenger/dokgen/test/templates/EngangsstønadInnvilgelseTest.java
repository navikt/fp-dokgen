package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class EngangsstønadInnvilgelseTest {

    private static final String TEMPLATE_NAME = "engangsstonad-innvilgelse";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testInnvFB");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_nynorsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "testInnvFB");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_på_engelsk() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "testInnvFB");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    //Varianter av samme mal
    @Test
    public void skal_generere_brev_for_død_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testDød");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-død_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_medhold_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testMedhold");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-medhold_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void skal_generere_brev_for_endret_sats_på_bokmål() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testEndretSats");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-endretSats_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

}
