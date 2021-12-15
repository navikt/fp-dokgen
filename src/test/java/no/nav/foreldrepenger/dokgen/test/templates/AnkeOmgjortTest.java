package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class AnkeOmgjortTest {

    private static final Brevmal BREVMAL = Brevmal.ANKE_OMGJORT;

    @Test
    public void anke_omgjort_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_anke_omgjort");
        var expected = getExpected(BREVMAL, "anke_omgjort_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void anke_omgjort_nn_skal_gi_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_anke_omgjort");
        var expected = getExpected(BREVMAL, "anke_omgjort_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void anke_omgjort_en_skal_gi_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_anke_omgjort");
        var expected = getExpected(BREVMAL, "anke_omgjort_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}