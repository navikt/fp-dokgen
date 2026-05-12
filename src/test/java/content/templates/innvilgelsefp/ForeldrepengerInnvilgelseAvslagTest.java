package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static content.support.TemplateTestUtil.getTestDataJson;
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

    @Test
    void prematur_avslag_4077_flerbarns_bruker_flertallsform() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_avslag");
        testDataJson.put("antallBarn", 2);
        var nb = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("kan ikke utsettes når barna er født før svangerskapsuke 33");
        assertThat(nn).contains("kan ikkje utsetjast når barna er fødd før svangerskapsveke 33");
        assertThat(en).contains("cannot be postponed when the children are born before the 33rd week of pregnancy");
    }
}
