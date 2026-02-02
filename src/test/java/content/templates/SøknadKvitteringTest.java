package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class SøknadKvitteringTest {

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nb_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nn_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_en_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_nb_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_nn_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.NYNORSK, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_en_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.ENGELSK, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nb() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nn() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.NYNORSK, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_en() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.ENGELSK, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_nb() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_nn() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.NYNORSK, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_en() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.ENGELSK, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nb() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nn() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.NYNORSK, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_en() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.ENGELSK, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
