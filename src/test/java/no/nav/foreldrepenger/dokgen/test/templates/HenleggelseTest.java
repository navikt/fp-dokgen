package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class HenleggelseTest {

    private static final String TEMPLATE_NAME = "henleggelse";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void henleggelse_innsyn() throws Exception {
        // Act
        String resultat = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_henleggelse_innsyn");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_innsyn_nb.txt"));
    }

    @Test
    public void henleggelse_klage() throws Exception {
        // Act
        String resultat = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_henleggelse_klage");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_klage_nb.txt"));
    }
    @Test
    public void vanligBehandling_nb() throws Exception {
        // Act
        String resultat = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_nb.txt"));
    }

    @Test
    public void vanligBehandling_nn() throws Exception {
        // Act
        String resultat = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_nn.txt"));
    }
    @Test
    public void vanligBehandling_en() throws Exception {
        // Act
        String resultat = compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "test_henleggelse_vanligBehandling");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_vanligBehandling_en.txt"));
    }
}
