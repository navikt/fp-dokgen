package no.nav.foreldrepenger.dokgen.test.templates;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;

public class EngangsstønadAvslagTest {

    private static final String TEMPLATE_NAME = "engangsstonad-avslag";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void avslagsbrev_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_avslag_fb");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-fb_nb.txt"));
    }

    @Test
    public void avslagsbrev_revurdering_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb","test_avslag_rv");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-rv_nb.txt"));
    }

}
