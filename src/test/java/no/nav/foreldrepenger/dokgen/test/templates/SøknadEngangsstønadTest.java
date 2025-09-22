package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class SøknadEngangsstønadTest {

    private static final Brevmal BREVMAL = Brevmal.ENGANGSSTØNAD_SØKNAD;

    @Test
    void bruker_har_mottatt_sykepenger_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_søknad");
        var expected = getExpected(BREVMAL, "mottatt_sykepenger_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
