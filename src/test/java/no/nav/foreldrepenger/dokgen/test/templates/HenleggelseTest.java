package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class HenleggelseTest {
    private static final Brevmal BREVMAL = Brevmal.INFO_OM_HENLEGGELSE;

    @Test
    void henleggelse_innsyn() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_innsyn");
        var expected = getExpected(BREVMAL, "henleggelse_innsyn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void henleggelse_klage() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_klage");
        var expected = getExpected(BREVMAL, "henleggelse_klage_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
