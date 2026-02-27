package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class SelvbetjeningTilsvarTilbakebetalingTest {
    private static final BrevMal BREVMAL = BrevMal.SELVBETJENING_TILSVAR_TILBAKEBETALINGVARSEL;

    @Test
    void tilsvar() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "tilsvar");
        var expected = getExpected(BREVMAL, "tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar_nynorsk_mappes_til_bokmål() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar_engelsk_mappes_til_bokmål() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
