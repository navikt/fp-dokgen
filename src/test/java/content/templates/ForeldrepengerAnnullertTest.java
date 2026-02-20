package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerAnnullertTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_ANNULLERT;

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
    void skal_generere_foreldrepenger_annullert_pga_innvilget_annen_part(Språk språk) {
        var content = compileContent(BREVMAL, språk, "test_fp_annullert_pga_annen_part_overtar_uttaket");
        var expected = getExpected(BREVMAL, "foreldrepenger-annullert_pga_annen_part_overtar_uttaket_%s.txt".formatted(språk.getKode()));
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
