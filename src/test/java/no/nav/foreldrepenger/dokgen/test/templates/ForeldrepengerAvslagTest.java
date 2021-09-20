package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class ForeldrepengerAvslagTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_AVSLAG;

    @Test
    public void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_mange"))
                .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-avslag_mange_nb.txt"));
    }

    @Test
    public void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_mange"))
                .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-avslag_mange_nn.txt"));
    }

    @Test
    public void skal_generere_foreldrepenger_avslag_brevet_med_de_fleste_avslagsårsakene_på_engelsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "test_mange"))
                .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-avslag_mange_en.txt"));
    }

    @Test
    public void skal_generere_foreldrepenger_avslag_brevet_med_fritekst() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fritekst"))
                .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-avslag_fritekst.txt"));
    }
}
