package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import content.support.BrevMal;
import content.support.Språk;

class ForeldrepengerOpphørTest {
    private static final BrevMal BREVMAL = BrevMal.FORELDREPENGER_OPPHØR;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String BRUKER_NAVN = "DONALD DUCK";
    private static final String STØNADSDATO_FOM = "01. januar 2021";
    private static final String STØNADSDATO_TOM = "24. mai 2021";
    private static final String BARN_DØDSDATO = "10. mai 2021";

    protected static final String AVSLAG_ÅRSAKER_LISTE = "avslagÅrsaker";
    protected static final String ER_SØKER_DØD = "erSøkerDød";
    protected static final String ANTALL_BARN = "antallBarn";
    protected static final String RELASJONS_ROLLE_TYPE = "relasjonsRolleType";

    @Test
    void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_opphør_for_død_barn_på_bokmål() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_barn_dør");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_barn_dør_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_opphør_for_død_barn_på_nynorsk() {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_barn_dør");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_barn_dør_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void skal_generere_foreldrepenger_opphør_for_død_barn_på_engelsk() {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_barn_dør");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_barn_dør_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void test_søker_død() {
        var testData = opprettTestData();
        testData.put(ER_SØKER_DØD, true);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains(String.format("Foreldrepengene til %s er stanset", "Donald Duck"))
            .contains(String.format("Vi har fått melding om at %s, som mottok foreldrepenger fra og med %s, er død.", BRUKER_NAVN, STØNADSDATO_FOM))
            .contains(String.format(
                "Vi sender dette brevet for å informere om at vi har stanset foreldrepengene. Siste dagen det ble utbetalt foreldrepenger var %s.",
                STØNADSDATO_TOM));
    }

    @Test
    void test_søker_ikke_død() {
        var testData = opprettTestData();
        testData.put(ER_SØKER_DØD, false);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du har ikke lenger rett til foreldrepenger");
    }

    @Test
    void test_kode_søker_1035() {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1035");

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains(
            "Du hadde ikke arbeidsinntekt eller inntekt som var likestilt med lønn i seks av de siste ti månedene før perioden med foreldrepenger startet. Derfor har du ikke rett til foreldrepenger.");
    }

    @Test
    void test_kode_søker_1027_medmor_1_barn() {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1027");
        testData.put(RELASJONS_ROLLE_TYPE, "MMOR");
        testData.put(ANTALL_BARN, 1);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke rett til foreldrepenger fordi du ikke var registrert som medmor til barnet.");
    }

    @Test
    void test_kode_søker_1027_far_2_barn() {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1027");
        testData.put(RELASJONS_ROLLE_TYPE, "FARA");
        testData.put(ANTALL_BARN, 2);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke rett til foreldrepenger fordi du ikke var registrert som far til barna.");
    }

    @Test
    void test_kode_søker_4072_2_barn() {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "4072");
        testData.put(ANTALL_BARN, 2);
        testData.put("barnDødsdato", BARN_DØDSDATO);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains(
            String.format("Vi har fått opplyst at barna dine døde %s. Den siste dagen din med foreldrepenger er derfor %s", BARN_DØDSDATO,
                STØNADSDATO_TOM));
    }

    @Test
    void test_kode_søker_1024_og_opphørdato() {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1024");

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).containsIgnoringWhitespaces(
            "Du hadde ikke rett til foreldrepenger fordi du ikke var medlem i folketrygden på det tidspunktet barna dine ble født.\nVi har ikke opplysninger om at du jobbet eller hadde familie som forsørget deg i Norge. Det var derfor ikke dokumentert at du hadde rett til opphold etter EØS-avtalen.");
    }

    private void opprettÅrsaker(final Map<String, Object> testData, String... årsaker) {
        var årsakArray = OBJECT_MAPPER.createArrayNode();
        for (var årsak : årsaker) {
            årsakArray.add(årsak);
        }
        testData.put(AVSLAG_ÅRSAKER_LISTE, årsakArray);
    }

    private Map<String, Object> opprettTestData() {
        var testData = new HashMap<String, Object>();
        opprettFelles(testData);
        testData.put(ER_SØKER_DØD, false);
        testData.put(ANTALL_BARN, 2);
        testData.put(RELASJONS_ROLLE_TYPE, "MMOR");
        testData.put("fomStønadsdato", STØNADSDATO_FOM);
        testData.put("tomStønadsdato", STØNADSDATO_TOM);
        testData.put("opphørDato", STØNADSDATO_TOM);
        testData.put("gjelderFødsel", true);
        testData.put("halvG", 50000);
        testData.put("klagefristUker", 6);
        testData.put("lovhjemmelForAvslag", "§ 14");
        return testData;
    }

    private void opprettFelles(final Map<String, Object> testData) {
        var felles = new HashMap<String, Object>();
        felles.put("søkerNavn", BRUKER_NAVN);
        felles.put("søkerPersonnummer", "12345 66789");
        felles.put("brevDato", "4. mai 2021");
        felles.put("erAutomatiskBehandlet", true);
        felles.put("saksnummer", "123456789");
        felles.put("erUtkast", false);
        testData.put("felles", felles);
    }

}
