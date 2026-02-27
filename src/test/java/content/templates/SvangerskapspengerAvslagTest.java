package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class SvangerskapspengerAvslagTest {
    private static final BrevMal BREVMAL = BrevMal.SVANGERSKAPSPENGER_AVSLAG;

    @Test
    void bruker_er_ikke_medlem_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_er_ikke_medlem");
        var expected = getExpected(BREVMAL, "bruker_er_ikke_medlem_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_er_ikke_medlem_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "bruker_er_ikke_medlem");
        var expected = getExpected(BREVMAL, "bruker_er_ikke_medlem_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_ikke_tilstrekkelig_opptjening_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ikke_tilstrekkelig_opptjening");
        var expected = getExpected(BREVMAL, "ikke_tilstrekkelig_opptjening_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_ikke_tilstrekkelig_opptjening_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ikke_tilstrekkelig_opptjening");
        var expected = getExpected(BREVMAL, "ikke_tilstrekkelig_opptjening_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_kan_arbeide_ingen_risiko_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ingen_risiko_ved_arbeidet");
        var expected = getExpected(BREVMAL, "ingen_risiko_å_arbeide_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_kan_arbeide_ingen_risiko_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ingen_risiko_ved_arbeidet");
        var expected = getExpected(BREVMAL, "ingen_risiko_å_arbeide_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_ikke_sendt_nødvendig_dokumentasjon_nb() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "manglende_dokumentasjon");
        var expected = getExpected(BREVMAL, "manglende_dokumentasjon_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void bruker_har_ikke_sendt_nødvendig_dokumentasjon_nn() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "manglende_dokumentasjon");
        var expected = getExpected(BREVMAL, "manglende_dokumentasjon_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
