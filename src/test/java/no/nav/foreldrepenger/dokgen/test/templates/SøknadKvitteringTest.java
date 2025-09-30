package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class SøknadKvitteringTest {

    @Test
    void svp_utenalandsopphold_avtalt_ferie_test() {
        var content = compileContent(Brevmal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(Brevmal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_test() {
        var content = compileContent(Brevmal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(Brevmal.ENGANGSSTØNAD_SØKNAD, "es.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_far_2af_frilans_gradering_utsettelse() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "far-2af-frilans");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr() {
        var content = compileContent(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
