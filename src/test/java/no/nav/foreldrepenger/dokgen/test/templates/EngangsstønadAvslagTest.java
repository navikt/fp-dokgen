package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class EngangsstønadAvslagTest {

    private static final Brevmal BREVMAL = Brevmal.ES_AVSLAG;

    @Test
    public void avslagsbrev_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_fb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL,"engangsstonad-avslag-fb_nb.txt"));
    }

    @Test
    public void avslagsbrev_revurdering_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_avslag_rv"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "engangsstonad-avslag-rv_nb.txt"));
    }

}
