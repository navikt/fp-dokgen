package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class KlageAvvistTest {

    private static final BrevMal BREVMAL = BrevMal.KLAGE_AVVIST;

    @Test
    void klage_avvist_fp_nfp_tilbakekreving_alle_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_nfp_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_alle_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_fp_nfp_tilbakekreving_alle_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_nfp_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_alle_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_fp_nfp_tilbakekreving_alle_en_på_nb() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_nfp_tilbakekreving_alle");
        var expected = getExpected(BREVMAL, "fp_nfp_tilbakekreving_alle_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_svp_nfp_ikke_tilbakekreving_en_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_nfp_ikke-tilbakekreving_en");
        var expected = getExpected(BREVMAL, "svp_nfp_ikke-tilbakekreving_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void klage_avvist_påklagd_vedtak() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_paklagd_vedtak");
        assertThat(content).contains("Nav har avvist klagen din på vedtaket om foreldrepenger");
    }

    @Test
    void klage_avvist_ikke_påklagd_vedtak() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_ikke_paklagd_vedtak");
        assertThat(content).contains("Nav har avvist klagen din på foreldrepenger");
    }
}
