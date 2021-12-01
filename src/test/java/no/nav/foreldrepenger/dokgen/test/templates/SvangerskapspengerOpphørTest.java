package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;
import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

public class SvangerskapspengerOpphørTest {
    private static final Brevmal BREVMAL = Brevmal.SVANGERSKAPSPENGER_OPPHØR;

    @Test
    public void bruker_har_mottatt_sykepenger() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "mottatt_sykepenger");
        var expected = getExpected(BREVMAL, "mottatt_sykepenger_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void test_søker_død() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_dod");
        var expected = getExpected(BREVMAL, "bruker_er_dod_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_er_ikke_medlem() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "medl_1024");
        var expected = getExpected(BREVMAL, "medl_1024_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void bruker_er_ikke_gravid() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "bruker_ikke_gravid");
        var expected = getExpected(BREVMAL, "bruker_ikke_gravid_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

}