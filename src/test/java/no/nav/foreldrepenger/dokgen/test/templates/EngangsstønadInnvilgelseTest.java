package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EngangsstønadInnvilgelseTest {

    private static final String TEMPLATE_NAME = "engangsstonad-innvilgelse";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "testInnvFB");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "testInnvFB");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt"));
    }

    //Varianter av samme mal
    @Test
    public void skal_generere_brev_for_død_på_bokmål() throws Exception {
        // Act
        String expectedFilname = TEMPLATE_NAME + "-død";
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "testDød");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, expectedFilname + "_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_medhold_på_bokmål() throws Exception {
        // Act
        String expectedFilname = TEMPLATE_NAME + "-medhold";
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "testMedhold");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, expectedFilname + "_nb.txt"));
    }

}
