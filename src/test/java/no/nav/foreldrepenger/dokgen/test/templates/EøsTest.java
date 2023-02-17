package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class EøsTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;

    @Test
    void far_der_mor_har_rett_eøs() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/far_der_mor_rett_eøs");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/far_der_mor_rett_eøs_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
