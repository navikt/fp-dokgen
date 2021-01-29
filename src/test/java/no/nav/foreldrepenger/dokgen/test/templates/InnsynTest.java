package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnsynTest {

    private static final String TEMPLATE_NAME = "innsyn";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void innsynInnvilget_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "innsyn_innvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_nb.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "innsyn_DelvisInnvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_delvisInnvilget_nb.txt"));
    }

    @Test
    public void innsynAvvist_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nb", "innsyn_avvist");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_nb.txt"));
    }

    @Test
    public void innsynAvvist_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "innsyn_avvist");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_nn.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "innsyn_DelvisInnvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_delvisInnvilget_nn.txt"));
    }

    @Test
    public void innsynInnvilget_nn() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "nn", "innsyn_innvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_nn.txt"));
    }

    @Test
    public void innsynAvvist_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "en", "innsyn_avvist");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_en.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "en", "innsyn_DelvisInnvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_delvisInnvilget_en.txt"));
    }

    @Test
    public void innsynInnvilget_en() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, "en", "innsyn_innvilget");

        // Assert
        assertThat(resultat).isEqualToNormalizingWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_en.txt"));
    }
}
