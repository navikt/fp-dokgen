package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class VarselOmRevurderingTest {
    private static final Brevmal BREVMAL = Brevmal.VARSEL_OM_REVURDERING;

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_ANNET");
        var expected = getExpected(BREVMAL, "varsel-revurdering_es_ANNET_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_ES_kode_ANNET_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_es_ANNET");
        var expected = getExpected(BREVMAL, "varsel-revurdering_es_ANNET_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_es_BARNIKKEREG");
        var expected = getExpected(BREVMAL, "varsel-revurdering_es_BARNIKKEREG_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_ES_kode_BARNIKKEREG_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_es_BARNIKKEREG");
        var expected = getExpected(BREVMAL, "varsel-revurdering_es_BARNIKKEREG_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_AKTIVITET_flere_opplysninger_2barn");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_AKTIVITET_flere_opplysninger_2barn_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_AKTIVITET_flere_opplysninger_og_2barn_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_AKTIVITET_flere_opplysninger_2barn");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_AKTIVITET_flere_opplysninger_2barn_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_IKKEOPPTJENT_ikke_flere_opplysninger_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_IKKEOPPTJENT_ikke_flere_opplysninger");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_IKKEOPPTJENT_ikke_flere_opplysninger_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_JOBBFULLTID");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_JOBBFULLTID_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_JOBBFULLTID_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_fp_JOBBFULLTID");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_JOBBFULLTID_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_FP_kode_ANNET_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_ANNET");
        var expected = getExpected(BREVMAL, "varsel-revurdering_fp_ANNET_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_JOBBFULLTID");
        var expected = getExpected(BREVMAL, "varsel-revurdering_svp_JOBBFULLTID_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBBFULLTID_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_svp_JOBBFULLTID");
        var expected = getExpected(BREVMAL, "varsel-revurdering_svp_JOBBFULLTID_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_brev_for_varsel_om_revurdering_SVP_kode_JOBB6MND_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_JOBB6MND");
        var expected = getExpected(BREVMAL, "varsel-revurdering_svp_JOBB6MND_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
