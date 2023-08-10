package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

class SelvbetjeningTilsvarTilbakebetalingTest {
    private static final Brevmal BREVMAL = Brevmal.SELVBETJENING_TILSVAR_TILBAKEBETALINGVARSEL;

    @Test
    void tilsvar() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "tilsvar");
        var expected = getExpected(BREVMAL, "tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar_nynorsk_mappes_til_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void ingen_tilsvar_engelsk_mappes_til_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "ingen_tilsvar");
        var expected = getExpected(BREVMAL, "ingen_tilsvar_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
