package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class ForeldrepengerInfoTilAnnenForelderTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER;

    @Test
    public void infobrev_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "infobrev"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nb.txt"));
    }

    @Test
    public void infobrev_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "infobrev"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nn.txt"));
    }

    @Test
    public void infobrev_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "infobrev"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_en.txt"));
    }

    @Test
    public void infobrev_opphold_nb() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "infobrev_opphold"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nb.txt"));
    }

    @Test
    public void infobrev_opphol_nn() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "infobrev_opphold"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nn.txt"));
    }

    @Test
    public void infobrev_opphold_en() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "infobrev_opphold"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_en.txt"));
    }
}

