package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerAvslagTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_AVSLAG;

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_en_enkelt_periode() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_enkeltperiode");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_enkeltperiode.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_fritekst() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fritekst");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_fritekst.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
