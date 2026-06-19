package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

/**
 * Dekker switch/case-grener i engangsstønad-avslag enkeltvis.
 * <p>
 * Denne malen bruker {{#switch avslagÅrsak}} der avslagÅrsak er en scalar streng (ikke array).
 * Mønsteret skiller seg fra foreldrepenger-opphør, som itererer over en array og refererer
 * {{../this}} inni case — det var kun sistnevnte kombinasjon som brøt ved handlebars 4.5.2.
 * Testene her låser scalar-switch-oppførselen så en framtidig endring i helper-logikken fanges.
 */
class EngangsstønadAvslagSwitchCaseTest {

    private static final BrevMal BREVMAL = BrevMal.ENGANGSSTØNAD_AVSLAG;

    @Test
    void case_søker_er_utvandret() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData("SØKER_ER_UTVANDRET"));
        assertThat(content).contains("Ifølge folkeregisteret har du flyttet fra Norge.");
    }

    @Test
    void case_søker_er_ikke_medlem() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData("SØKER_ER_IKKE_MEDLEM"));
        assertThat(content).contains("Du er omfattet av trygdeordningen i et annet land.");
    }

    @Test
    void case_søker_har_ikke_lovlig_opphold() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData("SØKER_HAR_IKKE_LOVLIG_OPPHOLD"));
        assertThat(content).contains("Du har ikke oppholdstillatelse i Norge.");
    }

    @Test
    void case_søker_har_ikke_oppholdsrett() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData("SØKER_HAR_IKKE_OPPHOLDSRETT"));
        assertThat(content).contains("Det er derfor ikke dokumentert at du har rett til opphold etter EØS-avtalen.");
    }

    @Test
    void case_med_nested_eq_ett_barn() {
        var testData = testData("IKKE_ALENEOMSORG");
        testData.put("antallBarn", 1);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("du ikke har aleneomsorgen for barnet ditt.");
    }

    @Test
    void case_med_nested_eq_flere_barn() {
        var testData = testData("IKKE_ALENEOMSORG");
        testData.put("antallBarn", 2);
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("du ikke har aleneomsorgen for barna dine.");
    }

    @Test
    void case_med_nested_if_medlemskapfom_satt() {
        var testData = testData("SØKER_INNFLYTTET_FOR_SENT");
        testData.put("medlemskapFom", "1. mars 2026");
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData);
        assertThat(content).contains("Vi har opplysninger om at du flyttet til Norge 1. mars 2026.");
    }

    @Test
    void ukjent_case_gir_ingen_avslagstekst() {
        var content = compileContent(BREVMAL, Språk.BOKMÅL, testData("EN_KODE_SOM_IKKE_FINNES"));
        assertThat(content).doesNotContain("Ifølge folkeregisteret har du flyttet fra Norge.");
    }

    private Map<String, Object> testData(String avslagÅrsak) {
        var testData = new HashMap<String, Object>();
        testData.put("førstegangsbehandling", true);
        testData.put("antallBarn", 1);
        testData.put("avslagÅrsak", avslagÅrsak);
        testData.put("klagefristUker", 6);

        var felles = new HashMap<String, Object>();
        felles.put("søkerNavn", "DONALD DUCK");
        felles.put("søkerPersonnummer", "12345 66789");
        felles.put("brevDato", "4. mai 2021");
        felles.put("erAutomatiskBehandlet", true);
        felles.put("saksnummer", "123456789");
        felles.put("erUtkast", false);
        testData.put("felles", felles);
        return testData;
    }
}
