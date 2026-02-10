package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class EngangsstønadAvslagTest {

    private static final BrevMal BREVMAL = BrevMal.ENGANGSSTØNAD_AVSLAG;

    @Test
    void avslagsbrev_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_fb");
        var expected = getExpected(BREVMAL, "engangsstonad-avslag-fb_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void avslagsbrev_revurdering_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_rv");
        var expected = getExpected(BREVMAL, "engangsstonad-avslag-rv_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
