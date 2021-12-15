package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class KlageHjemsendtTest {

    private static final Brevmal BREVMAL = Brevmal.KLAGE_HJEMSENDT;

    @Test
    public void klage_hjemsendt_fp_opphevet_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_opphevet_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_opphevet_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_hjemsendt_fp_opphevet_tilbakekreving_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_opphevet_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_opphevet_tilbakekreving_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_hjemsendt_fp_opphevet_tilbakekreving_en_på_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_opphevet_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_opphevet_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_hjemsendt_es_ikke_opphevet_ikke_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_ikke-opphevet_ikke-tilbakekreving");
        var expected = getExpected(BREVMAL, "es_ikke-opphevet_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
