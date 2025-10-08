package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

class UttalelseTilbakebetalingTest {

    @Test
    void uttalelse_om_tilbakebetaling_pdf_test() {
        var content = compileContent(Brevmal.UTTALELSE_TILBAKEBETALING, Språk.BOKMÅL, "svar");
        var expected = getExpected(Brevmal.UTTALELSE_TILBAKEBETALING, "uttalelse.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
