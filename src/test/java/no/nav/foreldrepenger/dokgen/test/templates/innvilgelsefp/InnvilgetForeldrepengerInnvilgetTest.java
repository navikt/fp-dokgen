package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerInnvilgetTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "innvilget";

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_prosent"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "innvilget-foreldrepenger_prosent_nb.txt"));
    }

    @Test
    public void skal_fortelle_om_innvilget_fullt_uttak_uten_prosent_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_fullt"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-foreldrepenger_fullt_nb.txt"));
    }

    @Test
    public void skal_liste_ut_arbeidsforhold_2_med_deltidstekst_og_arbeidsforhold_3_med_selvom_tekst_og_arbeidsforhold_4_med_permisjonstekst() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_arbeidsforhold"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-foreldrepenger_arbeidsforhold_nb.txt"));
    }

    @Test
    public void skal_liste_ut_annen_aktivitet_når_det_er_frilans_med_gradering_og_næring_når_det_er_gradering() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_annenaktivitet_næring"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-foreldrepenger_annenaktivitet_næring_nb.txt"));
    }

    @Test
    public void skal_liste_ut_tekster_for_alle_årsakene_som_finnes_i_periodelista() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_enkeltårsaker"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-foreldrepenger_enkeltårsaker_nb.txt"));
    }

    @Test
    public void skal_ikke_bruke_punktliste_når_bare_en_periode_er_innvilget() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_innvilget_og_avslått"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-foreldrepenger_innvilget_og_avslått_nb.txt"));
    }
}
