package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseDødtBarnTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "dodt_barn";

    @Test
    void dødt_barn_førstegangsbehandling_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void dødt_barn_førstegangsbehandling_nn() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void dødt_barn_førstegangsbehandling_en() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
