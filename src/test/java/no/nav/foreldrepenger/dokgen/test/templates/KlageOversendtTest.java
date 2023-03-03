package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class KlageOversendtTest {

    private static final Brevmal BREVMAL = Brevmal.KLAGE_OVERSENDT;

    @Test
    void klage_oversendt_fp_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_fp_tilbakekreving_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_fp_tilbakekreving_en_på_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_oversendt_svp_ikke_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_ikke-tilbakekreving");
        var expected = getExpected(BREVMAL, "svp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
