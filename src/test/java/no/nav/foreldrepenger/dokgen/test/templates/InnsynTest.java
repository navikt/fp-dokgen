package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class InnsynTest {

    private static final String TEMPLATE_NAME = "innsyn";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void innsynInnvilget_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_nb.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_delvis_innvilget_nb.txt"));
    }

    @Test
    public void innsynAvvist_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_nb.txt"));
    }

    @Test
    public void innsynAvvist_nn() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_nn.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nn() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_delvis_innvilget_nn.txt"));
    }

    @Test
    public void innsynInnvilget_nn() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nn", "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_nn.txt"));
    }

    @Test
    public void innsynAvvist_en() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_avvist_en.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_en() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_delvis_innvilget_en.txt"));
    }

    @Test
    public void innsynInnvilget_en() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "en", "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_innvilget_en.txt"));
    }
}
