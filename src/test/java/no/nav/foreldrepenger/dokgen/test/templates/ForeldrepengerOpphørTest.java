package no.nav.foreldrepenger.dokgen.test.templates;

import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.compileContent;
import static no.nav.foreldrepenger.dokgen.test.support.TemplateTestService.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import no.nav.foreldrepenger.dokgen.test.support.Brevmal;
import no.nav.foreldrepenger.dokgen.test.support.Språk;

public class ForeldrepengerOpphørTest {
    private static final Brevmal BREVMAL = Brevmal.FORELDREPENGER_OPPHØR;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String BRUKER_NAVN = "Donald Duck";
    private static final String STØNADSDATO_FOM = "01. januar 2021";
    private static final String STØNADSDATO_TOM = "24. mai 2021";
    private static final String BARN_DØDSDATO = "10. mai 2021";

    protected static final String AVSLAG_ÅRSAKER_LISTE = "avslagÅrsaker";
    protected static final String ER_SØKER_DØD = "erSøkerDød";
    protected static final String ANTALL_BARN = "antallBarn";
    protected static final String RELASJONSKODE = "relasjonskode";

    @Test
    public void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_bokmål() throws Exception {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_nynorsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.NYNORSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void skal_generere_foreldrepenger_opphør_brevet_med_de_fleste_avslagsårsakene_på_engelsk() throws Exception {
        var content = compileContent(BREVMAL, Språk.ENGELSK, "test_mange");
        var expected = getExpected(BREVMAL, "foreldrepenger-opphør_mange_en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void test_søker_død() throws Exception {
        var testData = opprettTestData();
        testData.put(ER_SØKER_DØD, true);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains(String.format("Foreldrepengene til %s er stanset", BRUKER_NAVN));
        assertThat(content).contains(String.format("Vi har fått melding om at %s, som mottok foreldrepenger fra og med %s, er død.", BRUKER_NAVN, STØNADSDATO_FOM));
        assertThat(content).contains(String.format("Vi sender dette brevet for å informere om at vi har stanset foreldrepengene. Siste dagen det ble utbetalt foreldrepenger var %s.", STØNADSDATO_TOM));
    }

    @Test
    public void test_søker_ikke_død() throws Exception {
        var testData = opprettTestData();
        testData.put(ER_SØKER_DØD, false);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du har ikke lenger rett til foreldrepenger");
    }

    @Test
    public void test_kode_søker_1035() throws Exception {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1035");

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke arbeidsinntekt eller inntekt som var likestilt med lønn i seks av de siste ti månedene før perioden med foreldrepenger startet. Derfor har du ikke rett til foreldrepenger.");
    }

    @Test
    public void test_kode_søker_1027_medmor_1_barn() throws Exception {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1027");
        testData.put(RELASJONSKODE,  "MEDMOR");
        testData.put(ANTALL_BARN,  1);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke rett til foreldrepenger fordi du ikke var registrert som medmor til barnet.");
    }

    @Test
    public void test_kode_søker_1027_far_2_barn() throws Exception {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1027");
        testData.put(RELASJONSKODE,  "FAR");
        testData.put(ANTALL_BARN,  2);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke rett til foreldrepenger fordi du ikke var registrert som far til barna.");
    }

    @Test
    public void test_kode_søker_4072_2_barn() throws Exception {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "4072");
        testData.put(ANTALL_BARN,  2);
        testData.put("barnDødsdato", BARN_DØDSDATO);

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains(String.format("Vi har fått opplyst at barna dine døde %s. Den siste dagen din med foreldrepenger er derfor %s", BARN_DØDSDATO, STØNADSDATO_TOM));
    }

    @Disabled //FIXME Michal - feiler lokalt hos meg
    @Test
    public void test_kode_søker_1024_og_opphørdato() throws Exception {
        var testData = opprettTestData();
        opprettÅrsaker(testData, "1024");

        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Du hadde ikke rett til foreldrepenger fordi du ikke var medlem i folketrygden på det tidspunktet barna dine ble født.\nVi har ikke opplysninger om at du jobbet eller hadde familie som forsørget deg i Norge. Det var derfor ikke dokumentert at du hadde rett til opphold etter EØS-avtalen.");
    }

    private void opprettÅrsaker(final ObjectNode testData, String... årsaker) {
        var årsakArray = OBJECT_MAPPER.createArrayNode();
        for (String årsak : årsaker) {
            årsakArray.add(årsak);
        }
        testData.set(AVSLAG_ÅRSAKER_LISTE, årsakArray);
    }

    private ObjectNode opprettTestData() {
        ObjectNode testData = OBJECT_MAPPER.createObjectNode();
        opprettFelles(testData);
        testData.put(ER_SØKER_DØD, false);
        testData.put(ANTALL_BARN,  2);
        testData.put(RELASJONSKODE,  "MEDMOR");
        testData.put("fomStønadsdato", STØNADSDATO_FOM);
        testData.put("tomStønadsdato", STØNADSDATO_TOM);
        testData.put("opphørDato", STØNADSDATO_TOM);
        testData.put("barnDødsdato", BARN_DØDSDATO);
        testData.put("gjelderFødsel",  true);
        testData.put("halvG",  50000);
        testData.put("klagefristUker",  6);
        testData.put("lovhjemmelForAvslag",  "§ 14");
        return testData;
    }

    private void opprettFelles(final ObjectNode testData) {
        ObjectNode felles = OBJECT_MAPPER.createObjectNode();
        felles.put("søkerNavn", BRUKER_NAVN);
        felles.put("søkerPersonnummer", "12345 66789");
        felles.put("brevDato", "4. mai 2021");
        felles.put("erAutomatiskBehandlet", true);
        felles.put("saksnummer", "123456789");
        felles.put("behandlesAvKA", false);
        felles.put("erUtkast", false);
        testData.set("felles", felles);
    }

}
