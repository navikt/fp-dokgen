package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnvilgetForeldrepengerBeregningTest {
    private static final Brevmal BREVMAL = Brevmal.FP_INNVILGET;
    private static final String UNDERMAL = "beregning";

    @Test
    public void undermal_beregning_en_ag_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_en_ag"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL, "innvilget-fp_en_ag_nb.txt"));
    }

    @Test
    public void undermal_beregning_to_ag_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_to_ag"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-fp_to_ag_nb.txt"));
    }

    @Test
    public void undermal_beregning_næring_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_næring"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-fp_næring_nb.txt"));
    }

    @Test
    public void undermal_beregning_frilans_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "førstegangsbehandling_frilans"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-fp_frilans_nb.txt"));
    }

    @Test
    public void undermal_beregning_fri_ytelse_80_dg_nb() throws Exception {
        assertThat(compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "revurdering_kun_ytelse_80_dg"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, UNDERMAL,"innvilget-fp_kun_ytelse_nb.txt"));
    }
}
