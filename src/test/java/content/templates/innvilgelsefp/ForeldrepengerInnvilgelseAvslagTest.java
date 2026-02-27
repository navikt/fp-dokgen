package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseAvslagTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;
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
