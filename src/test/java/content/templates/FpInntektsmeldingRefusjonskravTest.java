package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;

class FpInntektsmeldingRefusjonskravTest {
    private static final BrevMal BREVMAL = BrevMal.FP_INNTEKTSMELDING_REFUSJONSKRAV;

    @Test
    void alleFelterIInntektsmeldingen() {
        var content = compileContent(BREVMAL, null, "refusjonskravAgi");
        var expected = getExpected(BREVMAL, "refusjonskrav-agi.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
