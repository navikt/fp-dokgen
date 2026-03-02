package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseBeregningTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "beregning";

    @Test
    void undermal_beregning_en_ag_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_en_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_en_ag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_to_ag_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "førstegangsbehandling_to_ag");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_to_ag_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_næring_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_næring");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_næring_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_frilans_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_frilans");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_frilans_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_fri_ytelse_80_dg_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "revurdering_kun_ytelse_80_dg");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_kun_ytelse_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_besteberegning_seks_av_de_ti_beste_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_seksAvDeTiBeste");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_ja_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_besteberegning_ordinærDP_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_ordinær_kunDP");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_nei_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void undermal_beregning_besteberegning_ordinærDP_AT_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegang_besteberegning_ordinær_DP_AT");
        var expected = getExpected(BREVMAL, UNDERMAL, "fp-innvilgelse_besteberegning_nei_DP_AT_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
