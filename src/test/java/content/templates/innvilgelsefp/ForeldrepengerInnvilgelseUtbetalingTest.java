package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseUtbetalingTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;
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
