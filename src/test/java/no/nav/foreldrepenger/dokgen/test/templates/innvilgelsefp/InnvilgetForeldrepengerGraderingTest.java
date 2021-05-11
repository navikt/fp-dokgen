package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerGraderingTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/gradering_";
    private static final String EXPECTED_PREFIX = "gradering/innvilget-foreldrepenger";
    private static final TemplateTestService templateTestService = new TemplateTestService();

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        // Act
        String resultat = templateTestService.compileTemplateWithTestData(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "gradering/førstegangsbehandling_gradering_flere_arbgivere_og_gradering");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(templateTestService.getExpectedResult(TEMPLATE_NAME, EXPECTED_PREFIX + "_førstegangsbehandling_gradering_nb.txt"));
    }
}
