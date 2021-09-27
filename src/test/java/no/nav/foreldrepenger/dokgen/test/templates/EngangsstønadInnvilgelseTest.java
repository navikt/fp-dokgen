package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class EngangsstønadInnvilgelseTest {

    private static final Brevmal BREVMAL = Brevmal.ENGANGSSTØNAD_INNVILGELSE;

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "testInnvFB"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_ESførstegang_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "testInnvFB"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innvilgelse_på_engelsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "testInnvFB"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse_en.txt"));
    }

    //Varianter av samme mal
    @Test
    public void skal_generere_brev_for_død_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "testDød"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse-død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_medhold_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "testMedhold"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse-medhold_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_endret_sats_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "testEndretSats"))
            .isEqualToIgnoringNewLines(getExpected(BREVMAL, "engangsstonad-innvilgelse-endretSats_nb.txt"));
    }

}
