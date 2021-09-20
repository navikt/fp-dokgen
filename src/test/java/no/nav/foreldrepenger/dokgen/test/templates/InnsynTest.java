package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnsynTest {

    private static final Brevmal BREVMAL = Brevmal.INNSYN_SVAR;

    @Test
    public void innsynInnvilget_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_innvilget_nb.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_delvis_innvilget_nb.txt"));
    }

    @Test
    public void innsynAvvist_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_avvist_nb.txt"));
    }

    @Test
    public void innsynAvvist_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_avvist_nn.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_delvis_innvilget_nn.txt"));
    }

    @Test
    public void innsynInnvilget_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_innvilget_nn.txt"));
    }

    @Test
    public void innsynAvvist_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "innsyn_avvist"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_avvist_en.txt"));
    }

    @Test
    public void innsynDelvisInnvilget_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "innsyn_delvis_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_delvis_innvilget_en.txt"));
    }

    @Test
    public void innsynInnvilget_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "innsyn_innvilget"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innsyn_innvilget_en.txt"));
    }
}
