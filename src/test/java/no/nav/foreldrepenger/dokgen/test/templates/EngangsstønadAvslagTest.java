package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compile;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpectedResult;
import static org.assertj.core.api.Assertions.assertThat;

public class EngangsstønadAvslagTest {

    private static final String TEMPLATE_NAME = "engangsstonad-avslag";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void avslagsbrev_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_avslag_fb"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-fb_nb.txt"));
    }

    @Test
    public void avslagsbrev_revurdering_nb() throws Exception {
        assertThat(compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "test_avslag_rv"))
            .isEqualToIgnoringWhitespace(getExpectedResult(TEMPLATE_NAME, TEMPLATE_NAME + "-rv_nb.txt"));
    }

}
