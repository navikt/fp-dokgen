package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseUtbetalingTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "utbetaling";

    @Test
    void undermal_utbetaling_ingen_refusjon_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_ingen_refusjon");
        var expected = getExpected(BREVMAL, UNDERMAL, "utbetaling_ingen_refusjon_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_utbetaling_full_refusjon_rettigheter_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_full_refusjon");
        var expected = getExpected(BREVMAL, UNDERMAL, "utbetaling_full_refusjon_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_utbetaling_full_refusjon_rettigheter_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "førstegangsbehandling_full_refusjon");
        var expected = getExpected(BREVMAL, UNDERMAL, "utbetaling_full_refusjon_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_utbetaling_full_refusjon_rettigheter_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "førstegangsbehandling_full_refusjon");
        var expected = getExpected(BREVMAL, UNDERMAL, "utbetaling_full_refusjon_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_utbetaling_delvis_refusjon_rettigheter_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_delvis_refusjon");
        var expected = getExpected(BREVMAL, UNDERMAL, "utbetaling_delvis_refusjon_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
