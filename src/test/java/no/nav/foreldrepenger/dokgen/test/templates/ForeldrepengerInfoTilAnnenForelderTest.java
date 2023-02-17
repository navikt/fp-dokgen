package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class ForeldrepengerInfoTilAnnenForelderTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER;

    @Test
    public void infobrev_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "infobrev");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_ikke_sammenhengende_uttak_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "infobrev_ikke_sammenhengende_uttak");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_ikke_sammenhengende_uttak_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_ikke_sammenhengende_uttak_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "infobrev_ikke_sammenhengende_uttak");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_ikke_sammenhengende_uttak_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_ikke_sammenhengende_uttak_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "infobrev_ikke_sammenhengende_uttak");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_ikke_sammenhengende_uttak_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_opphold_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "infobrev_opphold");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void infobrev_opphol_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "infobrev_opphold");
        assertThat(content).isEqualToIgnoringNewLines(getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_nn.txt"));
    }

    @Test
    public void infobrev_opphold_en() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "infobrev_opphold");
        var expected = getExpected(BREVMAL, "foreldrepenger-infotilannenforelder_opphold_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}

