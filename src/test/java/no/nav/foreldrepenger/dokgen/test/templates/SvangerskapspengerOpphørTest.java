package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;
import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class SvangerskapspengerOpphørTest {
    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_OPPHØR;

    @Test
    public void skal_generere_svp_opphør_med_de_fleste_avslagsårsakene_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_mange");
        var expected = getExpected(BREVMAL, "mange_årsaker_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void test_søker_død() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_dod");
        var expected = getExpected(BREVMAL, "bruker_er_dod_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void test_medl_1024() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "medl_1024");
        var expected = getExpected(BREVMAL, "medl_1024_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}