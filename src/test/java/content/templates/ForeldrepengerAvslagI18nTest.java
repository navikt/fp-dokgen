package content.templates;

import static content.support.TemplateTestUtil.compileContentI18n;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerAvslagI18nTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_AVSLAG;

    @ParameterizedTest
    @EnumSource(Språk.class)
    void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene(Språk språk) {
        var content = compileContentI18n(BREVMAL, språk, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_mange_%s.txt".formatted(språk.getKode()));
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_en_enkelt_periode() {
        var content = compileContentI18n(BREVMAL, Språk.BOKMÅL, "test_enkeltperiode");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_enkeltperiode.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_avslag_brevet_med_fritekst() {
        var content = compileContentI18n(BREVMAL, Språk.BOKMÅL, "test_fritekst");
        var expected = getExpected(BREVMAL, "foreldrepenger-avslag_fritekst.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}