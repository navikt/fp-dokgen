package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

class ForeldrepengerAnnullertTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_ANNULLERT;

    @ParameterizedTest
    @EnumSource(Språk.class)
    void skal_generere_foreldrepenger_annullert_pga_søknad_fra_bruker(Språk språk) {
        var content = compileContent(BREVMAL, språk, "test_fp_annullert_pga_søknad_fra_bruker");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_pga_søknad_fra_bruker_%s.txt".formatted(språk.getKode()));
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @ParameterizedTest
    @EnumSource(Språk.class)
    void skal_generere_foreldrepenger_annullert_pga_innvilget_pleiepenger(Språk språk) {
        var content = compileContent(BREVMAL, språk, "test_fp_annullert_pga_innvilget_pleiepenger");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_pga_innvilget_pleiepenger_%s.txt".formatted(språk.getKode()));
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @ParameterizedTest
    @EnumSource(Språk.class)
    void skal_generere_foreldrepenger_annullert_pga_annen_part_overtar_uttakket(Språk språk) {
        var content = compileContent(BREVMAL, språk, "test_fp_annullert_pga_annen_part_overtar_uttaket");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_pga_innvilget_pleiepenger_%s.txt".formatted(språk.getKode()));
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
