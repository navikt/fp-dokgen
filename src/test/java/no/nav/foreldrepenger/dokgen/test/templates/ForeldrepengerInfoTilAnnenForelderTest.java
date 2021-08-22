package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class ForeldrepengerInfoTilAnnenForelderTest {
    private static final String TEMPLATE_NAME = "foreldrepenger-infotilannenforelder";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void infobrev_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "infobrev");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void infobrev_nn() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "infobrev");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void infobrev_en() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "infobrev");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void infobrev_opphold_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "infobrev_opphold");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void infobrev_opphol_nn() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "infobrev_opphold");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_nn.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void infobrev_opphold_en() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "infobrev_opphold");

        String expected = getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_en.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}

