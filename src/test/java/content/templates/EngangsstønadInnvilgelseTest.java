package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class EngangsstønadInnvilgelseTest {

    private static final BrevMal BREVMAL = BrevMal.ENGANGSSTØNAD_INNVILGELSE;

    @Test
    void skal_generere_brev_for_innvilgelse_ESførstegang_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "testInnvFB");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innvilgelse_ESførstegang_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "testInnvFB");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_innvilgelse_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "testInnvFB");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    //Varianter av samme mal
    @Test
    void skal_generere_brev_for_død_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "testDød");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse-død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_medhold_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "testMedhold");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse-medhold_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_endret_sats_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "testEndretSats");
        var expected = getExpected(BREVMAL, "engangsstonad-innvilgelse-endretSats_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
