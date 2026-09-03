package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.MarkdownUtil;

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
    void frilansoppdrag_med_tomme_datoer_skal_rendre_navnet_uten_periode() {
        var content = compileContent(BREVMAL, FRILANSOPPDRAG, Språk.BOKMÅL, frilansoppdrag("", ""));

        assertThat(content).contains("Kulturskolen").doesNotContain("Periode");
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
    void flere_frilansoppdrag_fra_samme_oppdragsgiver_skal_vises_med_antall_og_ytterperiode() {
        var oppdrag = frilansoppdrag(
            gruppe("Kulturskolen", 2, "2025-01-15", null),
            gruppe("Teaterlaget", 1, "2025-03-01", "2025-03-31"));

        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.BOKMÅL, oppdrag))
            .containsOnlyOnce("Kulturskolen")
            .containsOnlyOnce("Teaterlaget")
            .doesNotContain("<strong>Kulturskolen</strong>", "<strong>Teaterlaget</strong>")
            .contains("2 oppdrag: 15.01.2025 – Pågående")
            .contains("Periode: 01.03.2025 – 31.03.2025")
            .doesNotContain("30.06.2025", "01.07.2025");
        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.NYNORSK, oppdrag))
            .contains("2 oppdrag: 15.01.2025 – Pågåande");
        assertThat(compileContent(BREVMAL, FRILANSOPPDRAG, Språk.ENGELSK, oppdrag))
            .contains("2 assignments: 15.01.2025 – Ongoing");
    }

    @Test
    void nytt_frilanssvar_skal_utlede_om_søker_fortsatt_er_frilanser_fra_tom() {
        assertThat(compileContent(BREVMAL, "frilans_ny", Språk.BOKMÅL, frilanssvar(null)))
            .contains("Er du fortsatt frilanser: <strong>Ja</strong>");
        assertThat(compileContent(BREVMAL, "frilans_ny", Språk.BOKMÅL, frilanssvar("2025-09-20")))
            .contains("Er du fortsatt frilanser: <strong>Nei</strong>");
        assertThat(compileContent(BREVMAL, "frilans_ny", Språk.NYNORSK, frilanssvar(null)))
            .contains("Er du framleis frilansar: <strong>Ja</strong>");
        assertThat(compileContent(BREVMAL, "frilans_ny", Språk.ENGELSK, frilanssvar("2025-09-20")))
            .contains("Are you still a freelancer: <strong>No</strong>");
    }

    @Test
    void ny_flyt_uten_egen_næring_skal_ikke_rendre_næringsopplysninger() {
        var dokgen = new HashMap<String, Object>();
        dokgen.put("egenNæring", null);

        assertThat(compileContent(BREVMAL, "næring_ny", Språk.BOKMÅL, Map.of("_dokgen", dokgen))).isEmpty();
    }

    @Test
    void forelagt_næring_skal_beholde_listeelementene_inne_i_html_listen() {
        var egenNæring = Map.<String, Object>of(
            "forelagt", true,
            "fom", "2021-09-03",
            "hattVarigEndringAvNæringsinntektSiste4Kalenderår", false,
            "næringsinntekt", 350_000,
            "harBlittYrkesaktivILøpetAvDeTreSisteFerdigliknedeÅrene", false);
        var template = ContentUtil.lesRessursSomString(
            Path.of("/content/templates/søknad-felles/næring_ny_nb.hbs"));
        var markdown = new HandlebarsTjeneste().genererDokumentInnhold(
            template, Map.of("_dokgen", Map.of("egenNæring", egenNæring)));

        assertThat(MarkdownUtil.konverterTilHtml(markdown))
            .contains("<ul>", "<li>Du startet som selvstendig næringsdrivende", "</li>", "</ul>")
            .doesNotContain("&lt;li&gt;");
    }

    @Test
    void annen_inntekt_uten_arbeidsgiver_og_land_skal_beholde_perioden_inne_i_html_listen() {
        var template = ContentUtil.lesRessursSomString(
            Path.of("/content/templates/søknad-felles/andreInntekter_ny_nb.hbs"));
        var markdown = new HandlebarsTjeneste().genererDokumentInnhold(template,
            Map.of("andreInntekterSiste10Mnd", List.of(Map.of(
                "type", "MILITÆR_ELLER_SIVILTJENESTE",
                "fom", "2025-10-01",
                "tom", "2025-11-01"))));

        assertThat(MarkdownUtil.konverterTilHtml(markdown))
            .contains("<ul>", "<li>Periode: 01.10.2025 – 01.11.2025</li>", "</ul>")
            .doesNotContain("&lt;li&gt;");
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
        return frilansoppdrag(oppdrag("Kulturskolen", fom, tom));
    }

    @SafeVarargs
    private static Map<String, Object> frilansoppdrag(Map<String, Object>... oppdrag) {
        return Map.of("_dokgen", Map.of(
            "frilansoppdragForelagt", true,
            "grupperteFrilansoppdrag", List.of(oppdrag)));
    }

    private static Map<String, Object> oppdrag(String navn, String fom, String tom) {
        var oppdrag = new HashMap<String, Object>();
        oppdrag.put("navn", navn);
        oppdrag.put("antallOppdrag", 1);
        oppdrag.put("fom", fom);
        oppdrag.put("tom", tom);
        return oppdrag;
    }

    private static Map<String, Object> gruppe(String navn, int antallOppdrag, String fom, String tom) {
        var gruppe = oppdrag(navn, fom, tom);
        gruppe.put("antallOppdrag", antallOppdrag);
        return gruppe;
    }

    private static Map<String, Object> frilanssvar(String tom) {
        var frilans = new HashMap<String, Object>();
        frilans.put("oppstart", "2025-09-01");
        frilans.put("tom", tom);
        return Map.of(
            "frilans", frilans);
    }

    private static Map<String, Object> selvstendigNæring(String næringstype) {
        var næring = new HashMap<String, Object>();
        næring.put("navn", "Sagene Fiskeri");
        næring.put("organisasjonsnummer", "974760673");
        næring.put("næringstype", næringstype);
        return Map.of("_dokgen", Map.of(
            "selvstendigNæringForelagt", true),
            "søkerinfo", Map.of("selvstendigNæring", List.of(næring)));
    }
}
