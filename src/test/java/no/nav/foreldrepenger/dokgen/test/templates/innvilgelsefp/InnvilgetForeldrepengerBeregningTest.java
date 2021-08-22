package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerBeregningTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/beregning_";
    private static final String EXPECTED_PREFIX = "beregning/innvilget-foreldrepenger_";

    @Test
    public void undermal_beregning_en_ag_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_en_ag");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "en_ag_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void undermal_beregning_to_ag_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_to_ag");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "to_ag_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void undermal_beregning_næring_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_næring");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "næring_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void undermal_beregning_frilans_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/førstegangsbehandling_frilans");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "frilans_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    public void undermal_beregning_fri_ytelse_80_dg_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "beregning/revurdering_kun_ytelse_80_dg");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "kun_ytelse_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}
