package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerDødtBarnTest {
    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/dodt_barn_";
    private static final String EXPECTED_PREFIX = "dodt_barn/";

    @Test
    public void dødt_barn_førstegangsbehandling_nb() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "dodt_barn/dodt_barn_forstegangsbehandling"))
            .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, EXPECTED_PREFIX + "forstegangsbehandling_nb.txt"));
    }

}
