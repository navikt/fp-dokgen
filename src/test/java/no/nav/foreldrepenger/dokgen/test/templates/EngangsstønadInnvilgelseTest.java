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
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testInnvFB");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_nynorsk() throws Exception {
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "testInnvFB");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_på_engelsk() throws Exception {
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "testInnvFB");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_en.txt"));
    }

    //Varianter av samme mal
    @Test
    public void skal_generere_brev_for_død_på_bokmål() throws Exception {
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testDød");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_medhold_på_bokmål() throws Exception {
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testMedhold");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-medhold_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_endret_sats_på_bokmål() throws Exception {
        // Act
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "testEndretSats");

        // Assert
        assertThat(actual).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-endretSats_nb.txt"));
    }

}
