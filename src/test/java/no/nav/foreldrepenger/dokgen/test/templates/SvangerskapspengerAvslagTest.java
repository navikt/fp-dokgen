package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class SvangerskapspengerAvslagTest {
    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_AVSLAG;

    @Test
    public void bruker_er_ikke_medlem_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_er_ikke_medlem");
        var expected = getExpected(BREVMAL, "bruker_er_ikke_medlem_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_er_ikke_medlem_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "bruker_er_ikke_medlem");
        var expected = getExpected(BREVMAL, "bruker_er_ikke_medlem_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_har_ikke_tilstrekkelig_opptjening_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ikke_tilstrekkelig_opptjening");
        var expected = getExpected(BREVMAL, "ikke_tilstrekkelig_opptjening_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_har_ikke_tilstrekkelig_opptjening_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ikke_tilstrekkelig_opptjening");
        var expected = getExpected(BREVMAL, "ikke_tilstrekkelig_opptjening_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_kan_arbeide_ingen_risiko_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "ingen_risiko_ved_arbeidet");
        var expected = getExpected(BREVMAL, "ingen_risiko_å_arbeide_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_kan_arbeide_ingen_risiko_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "ingen_risiko_ved_arbeidet");
        var expected = getExpected(BREVMAL, "ingen_risiko_å_arbeide_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_har_ikke_sendt_nødvendig_dokumentasjon_nb() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "manglende_dokumentasjon");
        var expected = getExpected(BREVMAL, "manglende_dokumentasjon_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_har_ikke_sendt_nødvendig_dokumentasjon_nn() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "manglende_dokumentasjon");
        var expected = getExpected(BREVMAL, "manglende_dokumentasjon_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}
