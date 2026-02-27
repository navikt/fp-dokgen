package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class FritekstbrevHTMLTest {

    private static final BrevMal BREVMAL = BrevMal.FRITEKSTBREV_HTML;

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
