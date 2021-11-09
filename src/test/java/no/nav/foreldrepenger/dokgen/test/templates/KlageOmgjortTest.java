package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class KlageOmgjortTest {

    private static final Brevmal BREVMAL = Brevmal.KLAGE_OMGJORT;

    @Test
    public void klage_omgjort_fp_ka_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_ka_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_ka_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_omgjort_fp_ka_tilbakekreving_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_ka_tilbakekreving");
        var expected = getExpected(BREVMAL, "fp_ka_tilbakekreving_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_omgjort_es_nfp_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_nfp_tilbakekreving");
        var expected = getExpected(BREVMAL, "es_nfp_tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void klage_omgjort_svp_nfp_ikke_tilbakekreving_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_nfp_ikke-tilbakekreving");
        var expected = getExpected(BREVMAL, "svp_nfp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}