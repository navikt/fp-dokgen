package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SøknadVisningsdataBerikerTest {

    @Test
    void skalBeholdeLegacyflytNårRegisterfelteneManglerEllerErNull() {
        var søkerinfo = new HashMap<String, Object>();
        søkerinfo.put("frilansoppdrag", null);
        var input = Map.<String, Object>of("søkerinfo", søkerinfo);

        var resultat = SøknadVisningsdataBeriker.berik("søknad-foreldrepenger", input);

        assertThat(visningsdata(resultat))
            .containsEntry("nyAktivitetsflyt", false)
            .containsEntry("frilansoppdragForelagt", false)
            .containsEntry("selvstendigNæringForelagt", false);
        assertThat(input).doesNotContainKey("_dokgen");
    }

    @Test
    void skalVelgeNyFlytNårEttRegisterfeltErEnTomListe() {
        var resultat = SøknadVisningsdataBeriker.berik("søknad-svangerskapspenger",
            Map.of("søkerinfo", Map.of("frilansoppdrag", List.of())));

        assertThat(visningsdata(resultat))
            .containsEntry("nyAktivitetsflyt", true)
            .containsEntry("frilansoppdragForelagt", true)
            .containsEntry("selvstendigNæringForelagt", false)
            .containsEntry("grupperteFrilansoppdrag", List.of())
            .containsEntry("egenNæring", null);
    }

    @Test
    void skalBehandleTomtEgenNæringObjektSomFraværende() {
        var resultat = SøknadVisningsdataBeriker.berik("søknad-foreldrepenger",
            Map.of("søkerinfo", Map.of("frilansoppdrag", List.of()), "egenNæring", Map.of()));

        assertThat(visningsdata(resultat)).containsEntry("egenNæring", null);
    }

    @Test
    void skalGruppereFrilansoppdragOgBevareRekkefølgen() {
        var oppdrag = List.of(
            oppdrag("Kulturskolen", "2025-02-01", "2025-02-28"),
            oppdrag("Teaterlaget", "2025-04-01", "2025-04-30"),
            oppdrag("Kulturskolen", "2025-01-01", ""));
        var resultat = SøknadVisningsdataBeriker.berik("søknad-foreldrepenger",
            Map.of("søkerinfo", Map.of("frilansoppdrag", oppdrag)));

        assertThat(grupperteFrilansoppdrag(resultat))
            .satisfiesExactly(
                gruppe -> assertThat(gruppe)
                    .containsEntry("navn", "Kulturskolen")
                    .containsEntry("antallOppdrag", 2)
                    .containsEntry("fom", "2025-01-01")
                    .containsEntry("tom", null),
                gruppe -> assertThat(gruppe).containsExactlyInAnyOrderEntriesOf(
                    Map.of("navn", "Teaterlaget", "antallOppdrag", 1, "fom", "2025-04-01", "tom", "2025-04-30")));
    }

    @Test
    void skalMarkereOmBrukerensNæringBleForelagtUtenÅMutereInput() {
        var egenNæring = Map.<String, Object>of("organisasjonsnummer", "999999999", "navnPåNæringen", "Fisk AS");
        var input = Map.<String, Object>of(
            "søkerinfo", Map.of("selvstendigNæring", List.of(Map.of("organisasjonsnummer", "999999999"))),
            "egenNæring", egenNæring);

        var resultat = SøknadVisningsdataBeriker.berik("søknad-foreldrepenger", input);

        assertThat(egenNæring(resultat)).containsEntry("forelagt", true).containsEntry("navnPåNæringen", "Fisk AS");
        assertThat(egenNæring).doesNotContainKey("forelagt");
    }

    @Test
    void skalTåleUgyldigeDatoerFraRegistereneUtenÅFeile() {
        var resultat = SøknadVisningsdataBeriker.berik("søknad-foreldrepenger",
            Map.of("søkerinfo", Map.of("frilansoppdrag", List.of(oppdrag("Kulturskolen", "ikke-en-dato", "2025-02-28")))));

        assertThat(grupperteFrilansoppdrag(resultat)).singleElement()
            .satisfies(gruppe -> assertThat(gruppe).containsEntry("fom", null).containsEntry("tom", "2025-02-28"));
    }

    @Test
    void skalIkkeBerikeAndreDokumentmaler() {
        var input = Map.<String, Object>of("søkerinfo", Map.of("frilansoppdrag", List.of()));

        assertThat(SøknadVisningsdataBeriker.berik("annen-mal", input)).isSameAs(input);
    }

    private static Map<String, Object> oppdrag(String navn, String fom, String tom) {
        var oppdrag = new HashMap<String, Object>();
        oppdrag.put("navn", navn);
        oppdrag.put("fom", fom);
        oppdrag.put("tom", tom);
        return oppdrag;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> visningsdata(Map<String, Object> resultat) {
        return (Map<String, Object>) resultat.get("_dokgen");
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> grupperteFrilansoppdrag(Map<String, Object> resultat) {
        return (List<Map<String, Object>>) visningsdata(resultat).get("grupperteFrilansoppdrag");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> egenNæring(Map<String, Object> resultat) {
        return (Map<String, Object>) visningsdata(resultat).get("egenNæring");
    }
}
