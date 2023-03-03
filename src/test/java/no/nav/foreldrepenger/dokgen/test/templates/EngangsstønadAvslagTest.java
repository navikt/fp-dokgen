package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class EngangsstønadAvslagTest {

    private static final Brevmal BREVMAL = Brevmal.ENGANGSSTØNAD_AVSLAG;

    @Test
    void avslagsbrev_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_fb");
        var expected = getExpected(BREVMAL, "engangsstonad-avslag-fb_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void avslagsbrev_revurdering_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_rv");
        var expected = getExpected(BREVMAL, "engangsstonad-avslag-rv_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
