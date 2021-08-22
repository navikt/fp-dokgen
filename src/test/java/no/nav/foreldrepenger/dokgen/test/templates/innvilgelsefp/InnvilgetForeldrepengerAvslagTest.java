package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerAvslagTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/avslag_";
    private static final String EXPECTED_PREFIX = "avslag/innvilget-foreldrepenger";

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "avslag/førstegangsbehandling_avslag");

        String resulatUtenLinjeskift = resultat.replace("\n", "");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "_førstegangsbehandling_avslag_nb.txt"));
    }

}
