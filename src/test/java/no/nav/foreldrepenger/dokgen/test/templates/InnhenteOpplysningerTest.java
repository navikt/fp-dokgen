package no.nav.foreldrepenger.dokgen.test.templates;

import org.junit.jupiter.api.Test;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class InnhenteOpplysningerTest {
    private static final Brevmal BREVMAL = Brevmal.INNHENTE_OPPLYSNINGER;

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_es_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_på_engelsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.ENGELSK, "test_es_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_en.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_førstegang_død_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_es_fgb_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_revurdering"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_revurdering_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_revurdering_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_es_revurdering"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_revurdering_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_klage"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_klage_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_es_klage"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_klage_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_KA() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_klage_ka"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_klage_ka.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_klage_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_klage_død_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_ES_klage_død_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_es_klage_død"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_klage_død_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_fp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_førstegang_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_fp_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_fp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_svp_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_svp_fgb_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_SVP_førstegang_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_svp_fgb"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_svp_fgb_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_bokmål() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_fp_endringssøknad"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_fp_endringssøknad_nb.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_FP_endringssøknad_på_nynorsk() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.NYNORSK, "test_fp_endringssøknad"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_fp_endringssøknad_nn.txt"));
    }

    @Test
    public void skal_generere_brev_for_innhente_opplysninger_med_utkast() throws Exception {
        assertThat(compileContent(BREVMAL, Språk.BOKMÅL, "test_es_fgb_utkast"))
            .isEqualToIgnoringWhitespace(getExpected(BREVMAL, "innhente-opplysninger_es_fgb_utkast.txt"));
    }
}
