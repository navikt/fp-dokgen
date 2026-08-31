package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

/**
 * Registerdataene som ble forelagt søker er ikke under vår kontroll, og en allerede journalført søknad skal aldri kunne bli
 * ugjenskapelig. Malene må derfor tåle at enkeltfelter mangler uten at hele dokumentet feiler.
 */
class SøknadForelagteAktiviteterTest {

    private static final BrevMal BREVMAL = BrevMal.SØKNAD_FELLES;
    private static final String FRILANSOPPDRAG = "frilansoppdrag";
    private static final String REGISTRERT_NÆRING = "registrert_næring";

    @Test
    void frilansoppdrag_uten_fom_skal_rendre_navnet_uten_periode_nb() {
        var content = compileContent(BREVMAL, FRILANSOPPDRAG, Språk.BOKMÅL, frilansoppdrag(null, null));

        assertThat(content).contains("Kulturskolen").doesNotContain("Periode");
    }

    @Test
    void frilansoppdrag_uten_fom_skal_rendre_navnet_uten_periode_nn() {
        var content = compileContent(BREVMAL, FRILANSOPPDRAG, Språk.NYNORSK, frilansoppdrag(null, null));

        assertThat(content).contains("Kulturskolen").doesNotContain("Periode");
    }

    @Test
    void frilansoppdrag_uten_fom_skal_rendre_navnet_uten_periode_en() {
        var content = compileContent(BREVMAL, FRILANSOPPDRAG, Språk.ENGELSK, frilansoppdrag(null, null));

        assertThat(content).contains("Kulturskolen").doesNotContain("Period");
    }

    @Test
    void frilansoppdrag_med_fom_uten_tom_skal_vises_som_pågående() {
        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.BOKMÅL, frilansoppdrag("2025-03-01", null))).contains(
            "Periode: 01.03.2025 – Pågående");
        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.NYNORSK, frilansoppdrag("2025-03-01", null))).contains(
            "Periode: 01.03.2025 – Pågåande");
        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.ENGELSK, frilansoppdrag("2025-03-01", null))).contains(
            "Period: 01.03.2025 – Ongoing");
    }

    @Test
    void frilansoppdrag_med_fom_og_tom_skal_vise_hele_perioden() {
        var content = compileContent(BREVMAL, FRILANSOPPDRAG, Språk.BOKMÅL, frilansoppdrag("2025-01-15", "2025-06-30"));

        assertThat(content).contains("Periode: 15.01.2025 – 30.06.2025");
    }

    @Test
    void næring_uten_næringstype_skal_rendre_navnet_uten_tomt_kulepunkt_nb() {
        var content = compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.BOKMÅL, selvstendigNæring(null));

        assertThat(content).contains("Sagene Fiskeri").doesNotContain("Virksomhetstype");
    }

    @Test
    void næring_uten_næringstype_skal_rendre_navnet_uten_tomt_kulepunkt_nn() {
        var content = compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.NYNORSK, selvstendigNæring(null));

        assertThat(content).contains("Sagene Fiskeri").doesNotContain("Verksemdstype");
    }

    @Test
    void næring_uten_næringstype_skal_rendre_navnet_uten_tomt_kulepunkt_en() {
        var content = compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.ENGELSK, selvstendigNæring(null));

        assertThat(content).contains("Sagene Fiskeri").doesNotContain("Type of business");
    }

    @Test
    void næring_med_næringstype_skal_vise_virksomhetstype() {
        assertThat(compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.BOKMÅL, selvstendigNæring("FISKE"))).contains("Virksomhetstype: Fisker");
        assertThat(compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.NYNORSK, selvstendigNæring("FISKE"))).contains("Verksemdstype: Fiskar");
        assertThat(compileContent(BREVMAL, REGISTRERT_NÆRING, Språk.ENGELSK, selvstendigNæring("FISKE"))).contains("Type of business: Fisher");
    }

    private static Map<String, Object> frilansoppdrag(String fom, String tom) {
        var oppdrag = new HashMap<String, Object>();
        oppdrag.put("navn", "Kulturskolen");
        oppdrag.put("fom", fom);
        oppdrag.put("tom", tom);
        return Map.of("søkerinfo", Map.of("frilansoppdrag", List.of(oppdrag)));
    }

    private static Map<String, Object> selvstendigNæring(String næringstype) {
        var næring = new HashMap<String, Object>();
        næring.put("navn", "Sagene Fiskeri");
        næring.put("organisasjonsnummer", "974760673");
        næring.put("næringstype", næringstype);
        return Map.of("søkerinfo", Map.of("selvstendigNæring", List.of(næring)));
    }
}
