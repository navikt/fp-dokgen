package content.templates.innvilgelsefp;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerInnvilgelseAapPraksisendringTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_INNVILGELSE;
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
