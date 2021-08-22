package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerAvslagTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/avslag_";
    private static final String EXPECTED_PREFIX = "avslag/innvilget-foreldrepenger";

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        String actual = compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "avslag/førstegangsbehandling_avslag");

        String resulatUtenLinjeskift = actual.replace("\n", "");

        String expected = getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "_førstegangsbehandling_avslag_nb.txt");
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

}
