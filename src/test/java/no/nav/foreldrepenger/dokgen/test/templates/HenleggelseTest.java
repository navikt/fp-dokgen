package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class HenleggelseTest {
    private static final Brevmal BREVMAL = Brevmal.INFO_OM_HENLEGGELSE;

    @Test
    public void henleggelse_innsyn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_innsyn"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "henleggelse_innsyn_nb.txt"));
    }

    @Test
    public void henleggelse_klage() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_klage"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "henleggelse_klage_nb.txt"));
    }
    @Test
    public void vanligBehandling_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_henleggelse_vanligBehandling"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "henleggelse_vanligBehandling_nb.txt"));
    }

    @Test
    public void vanligBehandling_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_henleggelse_vanligBehandling"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "henleggelse_vanligBehandling_nn.txt"));
    }
    @Test
    public void vanligBehandling_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "test_henleggelse_vanligBehandling"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "henleggelse_vanligBehandling_en.txt"));
    }
}
