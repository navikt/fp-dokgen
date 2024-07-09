package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class FpInntektsmeldingTest {
    private static final Brevmal BREVMAL = Brevmal.FP_INNTEKTSMELDING;

    @Test
    void alleFelterIInntektsmeldingen() {
        var content = compileContent(BREVMAL, null, "inntektsmeldingMedAllleFelter");
        var expected = getExpected(BREVMAL, "alle-felter-i-inntektsmeldingen.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
