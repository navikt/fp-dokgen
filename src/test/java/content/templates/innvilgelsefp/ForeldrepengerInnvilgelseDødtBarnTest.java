package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseDødtBarnTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "dodt_barn";

    @Test
    void dødt_barn_førstegangsbehandling_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void dødt_barn_førstegangsbehandling_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void dødt_barn_førstegangsbehandling_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
