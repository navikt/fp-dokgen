package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerAvslagTest {
    private static final Brevmal BREVMAL = Brevmal.FP_INNVILGET;
    private static final String UNDERMAL = "avslag";

    @Test
    public void skal_fortelle_om_innvilget_uttak_med_prosent_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "avslag/førstegangsbehandling_avslag"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "førstegangsbehandling_avslag_nb.txt"));
    }

}
