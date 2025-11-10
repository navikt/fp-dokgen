package no.nav.foreldrepenger.dokgen.test.templates;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

class SøknadKvitteringTest {

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nb_test() {
        var content = compileContent(Brevmal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(Brevmal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nn_test() {
        var content = compileContent(Brevmal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(Brevmal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_en_test() {
        var content = compileContent(Brevmal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(Brevmal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_nb_test() {
        var content = compileContent(Brevmal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(Brevmal.ENGANGSSTØNAD_SØKNAD, "es-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_nn_test() {
        var content = compileContent(Brevmal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(Brevmal.ENGANGSSTØNAD_SØKNAD, "es-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
    @Test
    void engangsstønad_en_test() {
        var content = compileContent(Brevmal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(Brevmal.ENGANGSSTØNAD_SØKNAD, "es-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nb() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nn() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_en() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_nb() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-1-AF-fødsel");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_nn() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-1-AF-fødsel");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_en() {
        var content = compileContent(Brevmal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-1-AF-fødsel");
        var expected = getExpected(Brevmal.FORELDREPENGER_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nb() {
        var content = compileContent(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nn() {
        var content = compileContent(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_en() {
        var content = compileContent(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(Brevmal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
