package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ForeldrepengerAvslagTest {
    private static final String TEMPLATE_NAME = "foreldrepenger-avslag";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_bokmål() throws Exception {
        assertThat(compileContent(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_mange"))
                .isEqualToIgnoringWhitespace(getExpected(TEMPLATE_NAME, TEMPLATE_NAME + "_mange_nb.txt"));
    }
}
