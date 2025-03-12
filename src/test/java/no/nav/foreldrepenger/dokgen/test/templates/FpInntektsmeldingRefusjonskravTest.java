package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;

class FpInntektsmeldingRefusjonskravTest {
    private static final Brevmal BREVMAL = Brevmal.FP_INNTEKTSMELDING_REFUSJONSKRAV;

    @Test
    void alleFelterIInntektsmeldingen() {
        var content = compileContent(BREVMAL, null, "refusjonskravAgi");
        var expected = getExpected(BREVMAL, "refusjonskrav-agi.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
