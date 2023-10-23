package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseInnvilgetTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "innvilget";

    @Test
    void skal_fortelle_om_innvilget_uttak_med_prosent_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_prosent");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_prosent_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_fortelle_om_innvilget_fullt_uttak_uten_prosent_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_fullt");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_fullt_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_liste_ut_arbeidsforhold_2_med_deltidstekst_og_arbeidsforhold_3_med_selvom_tekst_og_arbeidsforhold_4_med_permisjonstekst() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_arbeidsforhold");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_arbeidsforhold_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_liste_ut_annen_aktivitet_når_det_er_frilans_med_gradering_og_næring_når_det_er_gradering() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_annenaktivitet_næring");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_annenaktivitet_næring_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_liste_ut_tekster_for_alle_årsakene_som_finnes_i_periodelista_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_enkeltårsaker");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_enkeltårsaker_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_liste_ut_tekster_for_alle_årsakene_som_finnes_i_periodelista_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "førstegangsbehandling_enkeltårsaker");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_enkeltårsaker_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_liste_ut_tekster_for_alle_årsakene_som_finnes_i_periodelista_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "førstegangsbehandling_enkeltårsaker");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_enkeltårsaker_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_ikke_bruke_punktliste_når_bare_en_periode_er_innvilget() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_innvilget_og_avslått");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger-innvilgelse_innvilget_og_avslått_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
