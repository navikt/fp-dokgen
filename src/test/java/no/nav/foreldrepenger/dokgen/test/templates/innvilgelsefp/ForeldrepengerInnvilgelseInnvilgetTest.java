package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getTestDataJson;
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

    @Test
    void skal_ekskludere_gjenværende_dager_hvis_avslagBarnOver3år() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("avslagBarnOver3år", true);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        assertThat(content).doesNotContain("igjen av kvoten din");
    }

    @Test
    void skal_ikke_oppgi_dagsats_i_utbetaling_hvis_varierendeDagsats() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("varierendeDagsats", true);
        testDataJson.put("starterMedFullUtbetaling", false);
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("Du får foreldrepenger fra og med 4. juni 2022. Den siste dagen du får foreldrepenger er");
        assertThat(nn).contains("Du får foreldrepengar frå og med 4. juni 2022. Den siste dagen du får foreldrepengar er");
        assertThat(en).contains("You will receive parental benefit starting from");
    }

    @Test
    void flerbarnsdager() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("antallBarn", 2);
        testDataJson.put("flerbarnsdagerUtvidetUker", 10);
        testDataJson.put("flerbarnsdagerUtvidetDager", 0);
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("forlenget foreldrepengeperioden med 10 uker fordi");
        assertThat(nn).contains("forlengd foreldrepengeperioden med 10 veker fordi");
        assertThat(en).contains("extended the parental benefit period by 10 weeks because");
    }

    @Test
    void flerbarnsdager_dager() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("antallBarn", 2);
        testDataJson.put("flerbarnsdagerUtvidetUker", 10);
        testDataJson.put("flerbarnsdagerUtvidetDager", 5);
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("forlenget foreldrepengeperioden med 10 uker og 5 dager fordi");
        assertThat(nn).contains("forlengd foreldrepengeperioden med 10 veker og 5 dagar fordi");
        assertThat(en).contains("extended the parental benefit period by 10 weeks and 5 days because");
    }

    @Test
    void flerbarnsdager_1_dager() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("antallBarn", 2);
        testDataJson.put("flerbarnsdagerUtvidetUker", 10);
        testDataJson.put("flerbarnsdagerUtvidetDager", 1);
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("forlenget foreldrepengeperioden med 10 uker og 1 dag fordi");
        assertThat(nn).contains("forlengd foreldrepengeperioden med 10 veker og 1 dag fordi");
        assertThat(en).contains("extended the parental benefit period by 10 weeks and 1 day because");
    }

    @Test
    void skal_oppgi_dagsats_i_utbetaling_hvis_varierendeDagsats_og_starterMedFullUtbetaling() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_prosent");
        testDataJson.put("varierendeDagsats", true);
        testDataJson.put("starterMedFullUtbetaling", true);
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("kroner per dag før skatt fra");
        assertThat(nn).contains("kroner per dag før skatt frå");
        assertThat(en).contains("kroner per day before taxes starting");
    }

    @Test
    void førstegangsbehandling_foreldrepenger_redusert_utbetaling() {
        var testDataJson = getTestDataJson(BREVMAL, UNDERMAL, "førstegangsbehandling_far_redusert_utbetaling");
        var nb = compileContent(BREVMAL, Språk.BOKMÅL, testDataJson);
        var nn = compileContent(BREVMAL, Språk.NYNORSK, testDataJson);
        var en = compileContent(BREVMAL, Språk.ENGELSK, testDataJson);

        assertThat(nb).contains("Barnets mor må jobbe minst 75 prosent for at du skal få utbetalt fulle foreldrepenger.");
        assertThat(nn).contains("Barnets mor må jobbe minst 75 prosent for at du skal få utbetalt fulle foreldrepengar.");
        assertThat(en).contains("The child's mother must work at least 75 percent for you to receive full parental benefits.");
    }
}
