package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class SvangerskapspengerInnvilgelseTest {

    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_INNVILGELSE;

    @Test
    public void svp_innvilgelse_fgb_at_fl_sn_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_at_fl_sn");
        var expected = getExpected(BREVMAL, "fgb_at_fl_sn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void svp_innvilgelse_fgb_sn_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fgb_sn");
        var expected = getExpected(BREVMAL, "fgb_sn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void svp_innvilgelse_revurdering_at_fl_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_revurdering_at_fl");
        var expected = getExpected(BREVMAL, "revurdering_at_fl.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
