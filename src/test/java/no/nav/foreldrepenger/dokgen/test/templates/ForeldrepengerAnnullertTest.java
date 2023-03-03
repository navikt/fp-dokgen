package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerAnnullertTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_ANNULLERT;

    @Test
    void skal_generere_foreldrepenger_annullert_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_annullert");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_annullert_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_annullert");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_annullert_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_fp_annullert");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
