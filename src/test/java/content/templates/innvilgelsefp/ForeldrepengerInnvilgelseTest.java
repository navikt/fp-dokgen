package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseTest {

    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;

    @Test
    void førstegangsbehandling_uten_gradering_og_avslag_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/automatisk_ingen_gradering_ingen_avslag");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/uten_gradering_avslag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void førstegangsbehandling_med_avslag_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_avslag_periode");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_avslag_periode_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void førstegangsbehandling_med_avslag_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "forstegangsbehandling/med_avslag_periode");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_avslag_periode_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void førstegangsbehandling_med_avslag_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "forstegangsbehandling/med_avslag_periode");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_avslag_periode_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void førstegangsbehandling_med_fritekst_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_fritekst");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_fritekst_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/gradering_flere_arbgivere_og_gradering");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/gradering_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_endret_endring_i_uttak() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_innvilget_endring_i_uttak_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_innvilget_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_innvilget_endring_i_uttak_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_innvilget_endring_i_uttak_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "revurdering/foreldrepenger_innvilget_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_innvilget_endring_i_uttak_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_innvilget_endring_i_uttak_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "revurdering/foreldrepenger_innvilget_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_innvilget_endring_i_uttak_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_endret_endring_i_beregning() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_beregning");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_endring_i_beregning_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void revurdering_foreldrepenger_dødt_barn() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_barn_dod");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_barn_dod_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
