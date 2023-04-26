package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseBeregningTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "beregning";

    @Test
    void undermal_beregning_en_ag_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_en_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_en_ag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_nn() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_en() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_næring_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_næring");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_næring_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_frilans_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_frilans");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_frilans_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_fri_ytelse_80_dg_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "revurdering_kun_ytelse_80_dg");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_kun_ytelse_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
    @Test
    void undermal_beregning_besteberegning_seks_av_de_ti_beste_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_seksAvDeTiBeste");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_ja_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
    @Test
    void undermal_beregning_besteberegning_ordinærDP_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_ordinær_kunDP");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_nei_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_besteberegning_ordinærDP_AT_nb() throws Exception {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_ordinær_DP_AT");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_nei_DP_AT_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
