package no.nav.foreldrepenger.dokgen.test.templates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class EngangsstønadAvslagTest {

    private static final String TEMPLATE_NAME = "engangsstonad-avslag";
    private static final String TEMPLATE_PATH = "/template_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void avslagsbrev_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_avslag_fb");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-fb_nb.txt"));
    }

    @Test
    public void avslagsbrev_revurdering_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb","test_avslag_rv");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-rv_nb.txt"));
    }

}
