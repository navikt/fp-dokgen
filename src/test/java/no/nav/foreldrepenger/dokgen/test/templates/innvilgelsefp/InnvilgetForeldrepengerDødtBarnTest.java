package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerDødtBarnTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "dodt_barn";

    @Test
    public void dødt_barn_førstegangsbehandling_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "dodt_barn_forstegangsbehandling");
        var expected = getExpected(BREVMAL, UNDERMAL, "forstegangsbehandling_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
