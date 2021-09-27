package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerTest {

    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;

    @Test
    public void førstegangsbehandling_uten_gradering_og_avslag_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/automatisk_ingen_gradering_ingen_avslag");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/uten_gradering_avslag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void førstegangsbehandling_med_avslag_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_avslag_periode");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_avslag_periode_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void førstegangsbehandling_med_fritekst_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_fritekst");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/med_fritekst_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/gradering_flere_arbgivere_og_gradering");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/gradering_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_uttak() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void revurdering_foreldrepenger_innvilget_endring_i_uttak() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_innvilget_endring_i_uttak");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_innvilget_endring_i_uttak_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_beregning() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_beregning");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_endring_i_beregning_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
    @Test
    public void revurdering_foreldrepenger_dødt_barn() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_barn_dod");
        var expected = getExpected(BREVMAL, "revurdering/foreldrepenger_endret_barn_dod_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
