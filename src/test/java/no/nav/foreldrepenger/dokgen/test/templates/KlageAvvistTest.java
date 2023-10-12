package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class KlageAvvistTest {

    private static final Brevmal BREVMAL = Brevmal.KLAGE_AVVIST;

    @Test
    void klage_avvist_fp_ka_tilbakekreving_alle_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_ka_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_ka_tilbakekreving_alle_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_fp_ka_tilbakekreving_alle_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_ka_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_ka_tilbakekreving_alle_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_fp_ka_tilbakekreving_alle_en_på_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_ka_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_ka_tilbakekreving_alle_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_svp_nfp_ikke_tilbakekreving_en_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_nfp_ikke-tilbakekreving_en");
        var expected = getExpected(BREVMAL, "svp_nfp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_påklagd_vedtak() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_paklagd_vedtak");
        assertThat(content).contains("NAV har avvist klagen din på vedtaket om foreldrepenger");
    }

    @Test
    void klage_avvist_ikke_påklagd_vedtak() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_ikke_paklagd_vedtak");
        assertThat(content).contains("NAV har avvist klagen din på foreldrepenger");
    }
}
