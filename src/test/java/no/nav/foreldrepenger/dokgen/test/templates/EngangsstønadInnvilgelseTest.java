package no.nav.foreldrepenger.dokgen.test.templates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class EngangsstønadInnvilgelseTest {

    private static final String TEMPLATE_NAME = "engangsstonad-innvilgelse";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_generere_brev_på_bokmål() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt"));
    }

    @Test
    public void skal_generere_brev_på_nynorsk() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test");

        // Assert
        assertThat(resultat).isEqualTo(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt"));
    }
}
