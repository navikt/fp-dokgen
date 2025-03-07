package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;
import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

class FritekstbrevHTMLTest {

    private static final Brevmal BREVMAL = Brevmal.FRITEKSTBREV_HTML;

    @Test
    void skal_generere_fritekstbrev_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fritekstbrev");
        var expected = getExpected(BREVMAL, "fritekstbrev_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_fritekstbrev_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fritekstbrev");
        var expected = getExpected(BREVMAL, "fritekstbrev_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_fritekstbrev_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fritekstbrev");
        var expected = getExpected(BREVMAL, "fritekstbrev_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
