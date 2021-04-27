package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ForeldrepengerInfoTilAnnenForelderTest {
    private static final String TEMPLATE_NAME = "foreldrepenger-infotilannenforelder";
    private static final String TEMPLATE_PATH = "/template_";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void infobrev_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH , "nb", "infobrev");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nb.txt"));
    }

    @Test
    public void infobrev_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH , "nn", "infobrev");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_nn.txt"));
    }

    @Test
    public void infobrev_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "en", "infobrev");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_en.txt"));
    }

    @Test
    public void infobrev_opphold_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "infobrev_opphold");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_nb.txt"));
    }

    @Test
    public void infobrev_opphol_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "infobrev_opphold");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_nn.txt"));
    }

    @Test
    public void infobrev_opphold_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "en", "infobrev_opphold");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_opphold_en.txt"));
    }
}

