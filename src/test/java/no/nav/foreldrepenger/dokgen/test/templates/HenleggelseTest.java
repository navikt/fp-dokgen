package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HenleggelseTest {

    private static final String TEMPLATE_NAME = "henleggelse";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void henleggelse_innsyn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_henleggelse_innsyn");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_innsyn_nb.txt"));
    }

    @Test
    public void henleggelse_klage() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_henleggelse_klage");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_klage_nb.txt"));
    }
    @Test
    public void vanligBehandling_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_nb.txt"));
    }

    @Test
    public void vanligBehandling_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_nn.txt"));
    }
    @Test
    public void vanligBehandling_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "en", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_en.txt"));
    }
}