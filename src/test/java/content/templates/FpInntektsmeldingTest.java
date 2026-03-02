package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;

class FpInntektsmeldingTest {
    private static final BrevMal BREVMAL = BrevMal.FP_INNTEKTSMELDING;

    @Test
    void alleFelterIInntektsmeldingen() {
        var content = compileContent(BREVMAL, null, "inntektsmeldingMedAllleFelter");
        var expected = getExpected(BREVMAL, "alle-felter-i-inntektsmeldingen.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
