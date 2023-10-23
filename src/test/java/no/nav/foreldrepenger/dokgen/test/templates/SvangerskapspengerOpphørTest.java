package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class SvangerskapspengerOpphørTest {
    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_OPPHØR;

    @Test
    void bruker_har_mottatt_sykepenger_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "mottatt_sykepenger");
        var expected = getExpected(BREVMAL, "mottatt_sykepenger_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_mottatt_sykepenger_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "mottatt_sykepenger");
        var expected = getExpected(BREVMAL, "mottatt_sykepenger_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_mottatt_sykepenger_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "mottatt_sykepenger");
        var expected = getExpected(BREVMAL, "mottatt_sykepenger_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void test_søker_død_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_dod");
        var expected = getExpected(BREVMAL, "bruker_er_dod_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void test_søker_død_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "bruker_dod");
        var expected = getExpected(BREVMAL, "bruker_er_dod_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void test_søker_død_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "bruker_dod");
        var expected = getExpected(BREVMAL, "bruker_er_dod_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_medlem_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "medl_1024");
        var expected = getExpected(BREVMAL, "medl_1024_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_medlem_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "medl_1024");
        var expected = getExpected(BREVMAL, "medl_1024_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_medlem_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "medl_1024");
        var expected = getExpected(BREVMAL, "medl_1024_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_gravid_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_ikke_gravid");
        var expected = getExpected(BREVMAL, "bruker_ikke_gravid_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_gravid_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "bruker_ikke_gravid");
        var expected = getExpected(BREVMAL, "bruker_ikke_gravid_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_gravid_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "bruker_ikke_gravid");
        var expected = getExpected(BREVMAL, "bruker_ikke_gravid_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void barn_død_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "barn_død");
        var expected = getExpected(BREVMAL, "barn_død_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void barn_død_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "barn_død");
        var expected = getExpected(BREVMAL, "barn_død_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void barn_død_en() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "barn_død");
        var expected = getExpected(BREVMAL, "barn_død_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
