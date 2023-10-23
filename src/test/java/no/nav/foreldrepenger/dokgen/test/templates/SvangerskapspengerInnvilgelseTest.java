package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class SvangerskapspengerInnvilgelseTest {

    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_INNVILGELSE;

    @Test
    void svp_innvilgelse_fgb_at_fl_sn_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_at_fl_sn");
        var expected = getExpected(BREVMAL, "fgb_at_fl_sn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_fgb_at_fl_sn_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fgb_at_fl_sn");
        var expected = getExpected(BREVMAL, "fgb_at_fl_sn_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_fgb_at_fl_sn_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fgb_at_fl_sn");
        var expected = getExpected(BREVMAL, "fgb_at_fl_sn_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_fgb_sn_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_sn");
        var expected = getExpected(BREVMAL, "fgb_sn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_fgb_sn_fritekst_beregning_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_sn_fritekst_beregning");
        var expected = getExpected(BREVMAL, "fgb_sn_nb_fritekst_beregning.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_revurdering_at_fl_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_revurdering_at_fl");
        var expected = getExpected(BREVMAL, "revurdering_at_fl.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_2_arbeidsforhold_1_periode_hver() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_2_at_1_periode_hver");
        var expected = getExpected(BREVMAL, "fgb_2_at_1_periode_hver.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_innvilgelse_1_arbeidsforhold_1_periode() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_1_at_1_periode");
        var expected = getExpected(BREVMAL, "fgb_1_at_1_periode.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
