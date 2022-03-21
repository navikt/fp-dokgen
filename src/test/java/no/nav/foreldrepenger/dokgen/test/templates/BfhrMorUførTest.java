package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;
import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

class BfhrMorUførTest {

    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;

    @Test
    void med_og_uten_aktkrav() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_og_uten_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_og_uten_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void uten_aktkrav() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void med_aktkrav() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
