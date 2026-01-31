package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class BfhrMorUførTest {

    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;

    @Test
    @DisplayName("Mor er ufør, med og uten aktivitetskrav dager, barn født etter 02.08.2022 (etter WLB).")
        /*
         *   "disponibleDager": 110,
         *   "disponibleDagerUtenAktivitetskrav": 75,
         *   "disponibleDagerMedAktivitetskrav": 35,
         *   "utenMinsterett": false,
         */
    void med_og_uten_aktkrav_etter_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_og_uten_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_og_uten_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    @DisplayName("Mor er ufør, med og uten aktivitetskrav dager, barn født før 02.08.2022 (før WLB).")
        /*
         *   "disponibleDager": 110,
         *   "disponibleDagerUtenAktivitetskrav": 75,
         *   "disponibleDagerMedAktivitetskrav": 35,
         *   "utenMinsterett": true,
         */
    void med_og_uten_aktkrav_før_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_og_uten_aktkrav_uten_minsterett");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav_uten_minsterett.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    @DisplayName("Mor er ufør, uten aktivitetskrav dager, barn født etter 02.08.2022 (etter WLB).")
        /*
         *   "disponibleDager": 75,
         *   "disponibleDagerUtenAktivitetskrav": 75,
         *   "disponibleDagerMedAktivitetskrav": 0,
         *   "utenMinsterett": false,
         */
    void uten_aktkrav_etter_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    @DisplayName("Mor er ufør, uten aktivitetskrav dager, barn født før 02.08.2022 (før WLB).")
        /*
         *   "disponibleDager": 75,
         *   "disponibleDagerUtenAktivitetskrav": 75,
         *   "disponibleDagerMedAktivitetskrav": 0,
         *   "utenMinsterett": true,
         */
    void uten_aktkrav_før_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav_uten_minsterett");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_uten_aktkrav_uten_minsterett.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    @DisplayName("Mor er ufør, med aktivitetskrav dager, barn født etter 02.08.2022 (etter WLB).")
        /*
         *   "disponibleDager": 75,
         *   "disponibleDagerUtenAktivitetskrav": 0,
         *   "disponibleDagerMedAktivitetskrav": 75,
         *   "utenMinsterett": false,
         */
    void med_aktkrav_etter_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    @DisplayName("Mor er ufør, med aktivitetskrav dager, barn født før 02.08.2022 (før WLB).")
        /*
         *   "disponibleDager": 75,
         *   "disponibleDagerUtenAktivitetskrav": 0,
         *   "disponibleDagerMedAktivitetskrav": 75,
         *   "utenMinsterett": true,
         */
    void med_aktkrav_før_wlb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav_uten_minsterett");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/bfhr_mor_ufør_med_aktkrav.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
