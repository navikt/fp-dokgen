package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class HenleggelseTest {
    private static final BrevMal BREVMAL = BrevMal.INFO_OM_HENLEGGELSE;

    @Test
    void henleggelse_innsyn() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_innsyn");
        var expected = getExpected(BREVMAL, "henleggelse_innsyn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void henleggelse_klage() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_klage");
        var expected = getExpected(BREVMAL, "henleggelse_klage_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void vanligBehandling_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_henleggelse_vanligBehandling");
        var expected = getExpected(BREVMAL, "henleggelse_vanligBehandling_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
