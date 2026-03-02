package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class EøsTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;

    @Test
    void far_der_mor_har_rett_eøs() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "forstegangsbehandling/far_der_mor_rett_eøs");
        var expected = getExpected(BREVMAL, "forstegangsbehandling/far_der_mor_rett_eøs_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
