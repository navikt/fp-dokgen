package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerTest {

    private static final Brevmal BREVMAL = Brevmal.FP_INNVILGET;

    @Test
    public void førstegangsbehandling_uten_gradering_og_avslag_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/automatisk_ingen_gradering_ingen_avslag"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forstegangsbehandling/uten_gradering_avslag_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_avslag_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_avslag_periode"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL,"forstegangsbehandling/med_avslag_periode_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_fritekst_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/med_fritekst"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forstegangsbehandling/med_fritekst_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/gradering_flere_arbgivere_og_gradering"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "forstegangsbehandling/gradering_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_uttak() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_uttak"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL,"revurdering/foreldrepenger_endret_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_innvilget_endring_i_uttak() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_innvilget_endring_i_uttak"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "revurdering/foreldrepenger_innvilget_endring_i_uttak_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_beregning() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_endring_i_beregning"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "revurdering/foreldrepenger_endret_endring_i_beregning_nb.txt"));
    }
    @Test
    public void revurdering_foreldrepenger_dødt_barn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "revurdering/foreldrepenger_endret_barn_dod"))
                .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "revurdering/foreldrepenger_endret_barn_dod_nb.txt"));
    }

}
