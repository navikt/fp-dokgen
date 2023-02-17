package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnsynTest {

    private static final Brevmal BREVMAL = Brevmal.INNSYN_SVAR;

    @Test
    public void innsynInnvilget_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynDelvisInnvilget_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynAvvist_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynAvvist_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynDelvisInnvilget_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynInnvilget_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynAvvist_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_avvist");
        var expected = getExpected(BREVMAL, "innsyn_avvist_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynDelvisInnvilget_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_delvis_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_delvis_innvilget_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void innsynInnvilget_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "innsyn_innvilget");
        var expected = getExpected(BREVMAL, "innsyn_innvilget_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
