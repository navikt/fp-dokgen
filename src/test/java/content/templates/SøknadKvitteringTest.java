package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import content.support.BrevMal;
import content.support.Språk;

class SøknadKvitteringTest {

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nb_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_nn_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_utenalandsopphold_avtalt_ferie_en_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-utenlandsopphold");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-utenlandsopphold-ferie-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_frilans_næring_arbeid_i_utlandet_nb_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-frilans-næring-arbeid-i-utlandet");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-frilans-næring-arbeid-i-utlandet-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_frilans_næring_arbeid_i_utlandet_nn_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-frilans-næring-arbeid-i-utlandet");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-frilans-næring-arbeid-i-utlandet-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_frilans_næring_arbeid_i_utlandet_en_test() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-frilans-næring-arbeid-i-utlandet");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-frilans-næring-arbeid-i-utlandet-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void svp_næring_uten_forelagte_aktiviteter_bruker_legacy_mal_nb() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-næring-uten-forelagte-aktiviteter");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-forelagte-aktiviteter-nb.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Opplysninger fra AA-registeret", "Opplysninger fra Enhetsregisteret")
                .contains("<li>Har du jobbet i utlandet de siste 4 ukene? <strong>Ja</strong></li>");
    }

    @Test
    void svp_næring_uten_forelagte_aktiviteter_bruker_legacy_mal_nn() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-næring-uten-forelagte-aktiviteter");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-forelagte-aktiviteter-nn.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Opplysningar frå AA-registeret", "Opplysningar frå Einingsregisteret")
                .contains("<li>Har du jobba i utlandet dei siste 4 vekene? <strong>Ja</strong></li>");
    }

    @Test
    void svp_næring_uten_forelagte_aktiviteter_bruker_legacy_mal_en() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-næring-uten-forelagte-aktiviteter");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-forelagte-aktiviteter-en.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Information from the AA Register",
                    "Information from the Central Coordinating Register for Legal Entities")
                .contains("<li>Have you worked abroad in the last 4 weeks? <strong>Yes</strong></li>");
    }

    @Test
    void svp_næring_uten_treff_i_registrene_bruker_ny_mal_nb() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-næring-uten-treff-i-registrene");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-treff-i-registrene-nb.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("Du er ikke registrert med noen frilansoppdrag.")
                .contains("Du er ikke registrert med noen næringer i Brønnøysundregistrene.")
                .contains("## Frilans")
                .contains("## Selvstendig næringsdrivende")
                .contains("### Dette har du oppgitt:")
                .contains("<ul>\n    <li>Navn på virksomheten: Utenlandsk Fiskeri AB</li>\n    <li>Registrert i land: Sverige</li>");
    }

    @Test
    void svp_næring_uten_treff_i_registrene_bruker_ny_mal_nn() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-næring-uten-treff-i-registrene");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-treff-i-registrene-nn.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("Du er ikkje registrert med nokon næringar i Brønnøysundregistera.")
                .contains("### Dette har du oppgitt:")
                .contains("<ul>\n    <li>Namn på verksemda: Utenlandsk Fiskeri AB</li>");
    }

    @Test
    void svp_næring_uten_treff_i_registrene_bruker_ny_mal_en() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-næring-uten-treff-i-registrene");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-næring-uten-treff-i-registrene-en.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("You are not registered with any businesses in the Brønnøysund Register Centre.")
                .contains("### You have provided the following:")
                .contains("<ul>\n    <li>Business name: Utenlandsk Fiskeri AB</li>");
    }

    @Test
    void ny_flyt_skal_ikke_stille_spørsmål_som_er_fjernet_i_søknadsdialogen() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-næring-uten-treff-i-registrene");
        assertThat(content)
                .doesNotContain("Har du jobbet i utlandet de siste 4 ukene?")
                .contains("## Arbeid i utlandet siste 4 uker");

        var foreldrepenger = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        assertThat(foreldrepenger)
                .doesNotContain("Har du hatt andre inntektskilder de siste 10 månedene?")
                .contains("## Andre inntekter siste 10 måneder");
    }

    @Test
    void alle_andre_inntektskilder_skal_vises_uten_tomme_punkter_nb() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-alle-andre-inntektskilder");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-alle-andre-inntektskilder-nb.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("""
                <strong>Etterlønn sluttpakke</strong>
                <ul>
                    <li>Periode: 01.10.2025 – 01.11.2025</li>
                </ul>
                <strong>Førstegangstjeneste</strong>
                <ul>
                    <li>Periode: 02.11.2025 – 31.01.2026</li>
                </ul>
                <strong>Jobb i utlandet</strong>
                <ul>
                    <li>Arbeidsgiver: Svensk Verkstad AB</li>
                    <li>Periode: 01.02.2026 – Pågående</li>
                    <li>Landet virksomheten er registrert i: Sverige</li>
                </ul>""");
    }

    @Test
    void alle_andre_inntektskilder_skal_vises_uten_tomme_punkter_nn() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.NYNORSK, "mor-termin-alle-andre-inntektskilder");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-alle-andre-inntektskilder-nn.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("""
                <strong>Førstegangsteneste</strong>
                <ul>
                    <li>Periode: 02.11.2025 – 31.01.2026</li>
                </ul>
                <strong>Jobb i utlandet</strong>
                <ul>
                    <li>Arbeidsgivar: Svensk Verkstad AB</li>
                    <li>Periode: 01.02.2026 – Pågåande</li>
                    <li>Landet verksemda er registrert i: Sverige</li>
                </ul>""");
    }

    @Test
    void alle_andre_inntektskilder_skal_vises_uten_tomme_punkter_en() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.ENGELSK, "mor-termin-alle-andre-inntektskilder");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-alle-andre-inntektskilder-en.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("""
                <strong>Severance pay package</strong>
                <ul>
                    <li>Period: 01.10.2025 – 01.11.2025</li>
                </ul>
                <strong>Military service</strong>
                <ul>
                    <li>Period: 02.11.2025 – 31.01.2026</li>
                </ul>
                <strong>Job abroad</strong>
                <ul>
                    <li>Employer: Svensk Verkstad AB</li>
                    <li>Period: 01.02.2026 – Ongoing</li>
                    <li>Country where the business is registered: Sweden</li>
                </ul>""");
    }

    @Test
    void ny_flyt_uten_andre_inntekter_skal_ikke_vise_seksjonen_nb() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.BOKMÅL, "svp-uten-andre-inntekter-ny-flyt");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-uten-andre-inntekter-ny-flyt-nb.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Andre inntektskilder", "Arbeid i utlandet");
    }

    @Test
    void ny_flyt_uten_andre_inntekter_skal_ikke_vise_seksjonen_nn() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.NYNORSK, "svp-uten-andre-inntekter-ny-flyt");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-uten-andre-inntekter-ny-flyt-nn.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Andre inntektskjelder", "Arbeid i utlandet");
    }

    @Test
    void ny_flyt_uten_andre_inntekter_skal_ikke_vise_seksjonen_en() {
        var content = compileContent(BrevMal.SVANGESKAPSPENGER_SØKNAD, Språk.ENGELSK, "svp-uten-andre-inntekter-ny-flyt");
        var expected = getExpected(BrevMal.SVANGESKAPSPENGER_SØKNAD, "svp-uten-andre-inntekter-ny-flyt-en.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .doesNotContain("Other sources of income", "Work abroad");
    }

    @Test
    void engangsstønad_nb_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.BOKMÅL, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_nn_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.NYNORSK, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void engangsstønad_en_test() {
        var content = compileContent(BrevMal.ENGANGSSTØNAD_SØKNAD, Språk.ENGELSK, "es");
        var expected = getExpected(BrevMal.ENGANGSSTØNAD_SØKNAD, "es-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nb() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nb.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("<ul>\n    <li>Du startet som selvstendig næringsdrivende");
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_nn() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.NYNORSK, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-nn.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("<ul>\n    <li>Du starta som sjølvstendig næringsdrivande");
    }

    @Test
    void foreldrepenger_mor_2af_frilans_gradering_utsettelse_en() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.ENGELSK, "mor-termin-2af-frilans-næring-andre-inntekter");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-fl-sn-andre-en.txt");
        assertThat(content)
                .isEqualToIgnoringNewLines(expected)
                .contains("<ul>\n    <li>You started as self-employed");
    }

    @Test
    void foreldrepenger_mor_af_nb() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.BOKMÅL, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_nn() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.NYNORSK, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void foreldrepenger_mor_af_en() {
        var content = compileContent(BrevMal.FORELDREPENGER_SØKNAD, Språk.ENGELSK, "mor-1-AF-fødsel");
        var expected = getExpected(BrevMal.FORELDREPENGER_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nb() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.BOKMÅL, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nb.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_nn() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.NYNORSK, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-nn.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    @Test
    void endringsøsknad_bfhr_en() {
        var content = compileContent(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, Språk.ENGELSK, "endring-bfhr");
        var expected = getExpected(BrevMal.FORELDREPNGER_ENDRING_SØKNAD, "foreldrepenger-en.txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }
}
