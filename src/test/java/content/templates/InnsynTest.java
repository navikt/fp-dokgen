package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class InnsynTest {

    private static final BrevMal BREVMAL = BrevMal.INNSYN_SVAR;

    @Test
    void innsynInnvilget_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynDelvisInnvilget_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynAvvist_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynAvvist_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynDelvisInnvilget_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynInnvilget_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynAvvist_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynDelvisInnvilget_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void innsynInnvilget_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
