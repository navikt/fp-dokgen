package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerInnvilgetTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/innvilget_";
    private static final String EXPECTED_PREFIX = "innvilget/innvilget-foreldrepenger_";

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_prosent"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "prosent_nb.txt"));
    }

    @Test
    public void skal_fortelle_om_innvilget_fullt_uttak_uten_prosent_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_fullt"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "fullt_nb.txt"));
    }

    @Test
    public void skal_liste_ut_arbeidsforhold_2_med_deltidstekst_og_arbeidsforhold_3_med_selvom_tekst_og_arbeidsforhold_4_med_permisjonstekst() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_arbeidsforhold"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "arbeidsforhold_nb.txt"));
    }

    @Test
    public void skal_liste_ut_annen_aktivitet_når_det_er_frilans_med_gradering_og_næring_når_det_er_gradering() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_annenaktivitet_næring"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "annenaktivitet_næring_nb.txt"));
    }

    @Test
    public void skal_liste_ut_tekster_for_alle_årsakene_som_finnes_i_periodelista() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_enkeltårsaker"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "enkeltårsaker_nb.txt"));
    }

    @Test
    public void skal_ikke_bruke_punktliste_når_bare_en_periode_er_innvilget() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "innvilget/førstegangsbehandling_innvilget_og_avslått"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "innvilget_og_avslått_nb.txt"));
    }
}
