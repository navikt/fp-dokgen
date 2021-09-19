package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerUtbetalingTest {
    private static final Brevmal BREVMAL = Brevmal.FP_INNVILGET;
    private static final String UNDERMAL = "utbetaling";

    @Test
    public void undermal_utbetaling_ingen_refusjon_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_ingen_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "utbetaling_ingen_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_full_refusjon_rettigheter_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_full_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "utbetaling_full_refusjon_nb.txt"));
    }

    @Test
    public void undermal_utbetaling_delvis_refusjon_rettigheter_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_delvis_refusjon"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "utbetaling_delvis_refusjon_nb.txt"));
    }
}
