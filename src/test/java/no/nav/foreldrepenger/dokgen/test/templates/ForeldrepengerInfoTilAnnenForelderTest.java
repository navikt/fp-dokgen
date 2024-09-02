package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerInfoTilAnnenForelderTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER;

    @Test
    void infobrev_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void infobrev_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void infobrev_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }


    @Test
    void infobrev_opphold_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "infobrev_opphold");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void infobrev_opphol_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "infobrev_opphold");
        assertThat(content).isEqualToIgnoringNewLines(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nn.txt"));
    }

    @Test
    void infobrev_opphold_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "infobrev_opphold");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}

