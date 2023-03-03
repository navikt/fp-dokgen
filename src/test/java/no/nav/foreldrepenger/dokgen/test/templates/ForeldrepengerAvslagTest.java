package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerAvslagTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_AVSLAG;

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_en_enkelt_periode() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_enkeltperiode");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_enkeltperiode.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_fritekst() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fritekst");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_fritekst.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
