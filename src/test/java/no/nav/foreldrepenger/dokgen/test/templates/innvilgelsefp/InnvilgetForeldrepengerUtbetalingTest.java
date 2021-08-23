package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerUtbetalingTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/utbetaling_";
    private static final String EXPECTED_PREFIX = "utbetaling/innvilget-foreldrepenger";

    @Test
    public void undermal_utbetaling_ingen_refusjon_nb() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "utbetaling/førstegangsbehandling_ingen_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, EXPECTED_PREFIX + "_utbetaling_ingen_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_full_refusjon_rettigheter_nb() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "utbetaling/førstegangsbehandling_full_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, EXPECTED_PREFIX + "_utbetaling_full_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_delvis_refusjon_rettigheter_nb() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "utbetaling/førstegangsbehandling_delvis_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, EXPECTED_PREFIX + "_utbetaling_delvis_refusjon_nb.txt"));
    }
}
