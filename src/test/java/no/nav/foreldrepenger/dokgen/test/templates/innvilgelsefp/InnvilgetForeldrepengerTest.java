package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerTest {

    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void førstegangsbehandling_uten_gradering_og_avslag_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/automatisk_ingen_gradering_ingen_avslag");

        String expected = getExpectedResult(TEMPLATE_NAME, "forstegangsbehandling/forstegangsbehandling_uten_gradering_avslag_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void førstegangsbehandling_med_avslag_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/med_avslag_periode");

        String expected = getExpectedResult(TEMPLATE_NAME, "forstegangsbehandling/forstegangsbehandling_med_avslag_periode_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void førstegangsbehandling_med_fritekst_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/med_fritekst");
        String expected = getExpectedResult(TEMPLATE_NAME, "forstegangsbehandling/forstegangsbehandling_med_fritekst_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/gradering_flere_arbgivere_og_gradering");

        String expected = getExpectedResult(TEMPLATE_NAME, "forstegangsbehandling/forstegangsbehandling_gradering_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_uttak() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_endret_endring_i_uttak");

        String expected = getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_endret_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void revurdering_foreldrepenger_innvilget_endring_i_uttak() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_innvilget_endring_i_uttak");

        String expected = getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_innvilget_endring_i_uttak_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_beregning() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_endret_endring_i_beregning");

        String expected = getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_endret_endring_i_beregning_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

}
