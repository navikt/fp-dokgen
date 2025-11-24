package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInnvilgelseAapPraksisendringTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INNVILGELSE;
    private static final String UNDERMAL = "aap_praksisendring";

    @Test
    void skal_informere_om_aap_praksisk_endring_nb() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.BOKMÅL, "foreldrepenger_aap_praksisendring");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger_aap_praksisendring_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_informere_om_aap_praksisk_endring_nn() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.NYNORSK, "foreldrepenger_aap_praksisendring");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger_aap_praksisendring_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_informere_om_aap_praksisk_endring_en() {
        var content = compileContent(BREVMAL, UNDERMAL, Språk.ENGELSK, "foreldrepenger_aap_praksisendring");
        var expected = getExpected(BREVMAL, UNDERMAL, "foreldrepenger_aap_praksisendring_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
