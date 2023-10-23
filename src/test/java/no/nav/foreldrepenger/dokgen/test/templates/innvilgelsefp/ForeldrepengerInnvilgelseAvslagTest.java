package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseAvslagTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "avslag";

    @Test
    void skal_fortelle_om_innvilget_uttak_med_prosent_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_avslag");
        var expected = getExpected(BREVMAL, UNDERMAL, "førstegangsbehandling_avslag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_fortelle_om_innvilget_uttak_med_prosent_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "førstegangsbehandling_avslag");
        var expected = getExpected(BREVMAL, UNDERMAL, "førstegangsbehandling_avslag_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_fortelle_om_innvilget_uttak_med_prosent_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "førstegangsbehandling_avslag");
        var expected = getExpected(BREVMAL, UNDERMAL, "førstegangsbehandling_avslag_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
