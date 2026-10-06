package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getTestDataJson;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil.getJsonMapFromString;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import content.support.BrevMal;
import content.support.Språk;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentCssStyling;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentGeneratorTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentSpråk;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.jsonschema.JsonSchemaTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.pdf.PdfGeneratorTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil;

class FellesUttaksplanKvitteringTest {

    private static final String NY_PLAN = """
        {
          "ønskerJustertUttakVedFødsel": true,
          "perioder": [
            {"fom": "2027-01-01", "tom": "2027-01-31", "søker": null,
             "annenPart": {"forelder": "MOR", "kontoType": "MØDREKVOTE", "flerbarnsdager": false}},
            {"fom": "2027-02-01", "tom": "2027-02-28",
             "annenPartEøs": {"kontoType": "FORELDREPENGER", "trekkdager": 20}},
            {"fom": "2027-03-01", "tom": "2027-03-31",
             "søker": {"forelder": "FAR_MEDMOR", "kontoType": "FELLESPERIODE",
               "morsAktivitet": "ARBEID", "samtidigUttak": 50.5, "flerbarnsdager": true,
               "gradering": {"arbeidstidprosent": 25.5,
                 "aktivitet": {"type": "ORDINÆRT_ARBEID", "arbeidsgiverNavn": "Testbedrift",
                   "arbeidsgiver": {"id": "999999999", "type": "ORGANISASJON"}}},
               "resultat": {"innvilget": true, "trekkerMinsterett": false, "trekkerDager": true, "årsak": "ANNET"}},
             "annenPart": {"forelder": "MOR", "kontoType": "MØDREKVOTE", "flerbarnsdager": false}},
            {"fom": "2027-04-01", "tom": "2027-04-30",
             "søker": {"forelder": "FAR_MEDMOR", "utsettelseÅrsak": "ARBEID", "flerbarnsdager": false,
               "resultat": {"innvilget": false, "trekkerMinsterett": false, "trekkerDager": false,
                 "årsak": "AVSLAG_UTSETTELSE_TILBAKE_I_TID"}}},
            {"fom": "2027-05-01", "tom": "2027-05-31",
             "søker": {"forelder": "FAR_MEDMOR", "utsettelseÅrsak": "FERIE", "flerbarnsdager": false}},
            {"fom": "2027-06-01", "tom": "2027-06-30",
             "søker": {"forelder": "FAR_MEDMOR", "utsettelseÅrsak": "FRI", "flerbarnsdager": false}}
          ]
        }
        """;

    @ParameterizedTest
    @MethodSource("søknader")
    void ny_plan_vises_direkte_uten_gammel_plan_eller_filtrering_på_resultat(BrevMal mal, Språk språk) {
        var data = data(mal);
        data.put("uttaksplan", getJsonMapFromString(NY_PLAN));
        data.put("dekningsgrad", "80");
        data.put("endringstidspunkt", "2027-05-01");
        settAntallBarnOgAnnenForeldersRett(data, 2, true);
        var original = JacksonUtil.JSON_MAPPER.writeValueAsString(data);

        var innhold = compileContent(mal, språk, data);

        assertThat(innhold).contains("01.03.2027", "31.03.2027", "01.04.2027", "01.05.2027", "01.06.2027",
                "Testbedrift (999999999)", "25.5", "50.5")
            .doesNotContain("01.01.2027", "01.02.2027", "100 prosent", "100 per cent");
        switch (språk) {
            case BOKMÅL -> assertThat(innhold).contains("80 prosent", "Fellesperiode", "Mors aktivitet i perioden: Arbeid",
                "Årsak til utsettelse: Arbeid", "Årsak til utsettelse: Ferie", "Periode uten uttak", "Skal bruke flerbarnsdager");
            case NYNORSK -> assertThat(innhold).contains("80 prosent", "Fellesperiode", "Moras aktivitet i perioden: Arbeid",
                "Årsak til utsetjing: Arbeid", "Årsak til utsetjing: Ferie", "Periode utan uttak", "Skal bruke fleirbarnsdagar");
            case ENGELSK -> assertThat(innhold).contains("80 per cent", "Shared period", "Mother's activity during the period: Work",
                "Reason for postponement: Work", "Reason for postponement: Holiday", "Period without withdrawal", "Should use multiple-child days");
        }
        if (mal == BrevMal.FORELDREPNGER_ENDRING_SØKNAD) {
            assertThat(innhold).contains(switch (språk) {
                case BOKMÅL -> "endring fra og med 01.03.2027";
                case NYNORSK -> "endring frå og med 01.03.2027";
                case ENGELSK -> "change from 01.03.2027";
            });
        }
        assertThat(JacksonUtil.JSON_MAPPER.writeValueAsString(data)).isEqualTo(original);
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void fraværende_og_null_perioder_beholder_gammel_visning(BrevMal mal, Språk språk) {
        var data = data(mal);
        var utenPerioder = compileContent(mal, språk, data);
        plan(data).put("perioder", null);

        assertThat(compileContent(mal, språk, data)).isEqualTo(utenPerioder);
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void tom_ny_plan_overstyrer_gammel_plan(BrevMal mal, Språk språk) {
        var data = data(mal);
        var plan = plan(data);
        plan.put("perioder", List.of());
        var medGammelPlan = compileContent(mal, språk, data);
        plan.remove("uttaksperioder");

        assertThat(compileContent(mal, språk, data)).isEqualTo(medGammelPlan);
        assertThat(medGammelPlan).doesNotContain("virkedager)", "business days)", "verkedagar)", "undefined", "null");
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void ny_plan_overstyrer_motstridende_gammel_plan(BrevMal mal, Språk språk) {
        var data = data(mal);
        var nyPlan = getJsonMapFromString(NY_PLAN);
        nyPlan.put("uttaksperioder", plan(data).get("uttaksperioder"));
        data.put("uttaksplan", nyPlan);
        var beggePlaner = compileContent(mal, språk, data);
        nyPlan.remove("uttaksperioder");

        assertThat(compileContent(mal, språk, data)).isEqualTo(beggePlaner);
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void bare_annen_forelders_perioder_gir_ingen_søkerperioder(BrevMal mal, Språk språk) {
        var data = data(mal);
        plan(data).put("perioder", ((List<?>) getJsonMapFromString(NY_PLAN).get("perioder")).subList(0, 2));
        var annenForeldersPlan = compileContent(mal, språk, data);
        plan(data).put("perioder", List.of());

        assertThat(compileContent(mal, språk, data)).isEqualTo(annenForeldersPlan);
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void samtidig_uttak_vises_bare_når_oppgitt_og_flerbarnsdager_vises_bare_når_valgt(BrevMal mal, Språk språk) {
        var data = data(mal);
        data.put("uttaksplan", getJsonMapFromString(NY_PLAN));
        var samtidigUttak = switch (språk) {
            case BOKMÅL -> "Skal annen forelder ha foreldrepenger i samme periode: <strong>Ja</strong>";
            case NYNORSK -> "Skal annan forelder ha foreldrepengar i same periode: <strong>Ja</strong>";
            case ENGELSK -> "Should the other parent receive parental benefit in the same period: <strong>Yes</strong>";
        };
        var samtidigUttakNei = samtidigUttak.replaceAll("<strong>(Ja|Yes)</strong>", språk == Språk.ENGELSK ? "<strong>No</strong>" : "<strong>Nei</strong>");
        var flerbarnsdager = switch (språk) {
            case BOKMÅL -> "Skal bruke flerbarnsdager";
            case NYNORSK -> "Skal bruke fleirbarnsdagar";
            case ENGELSK -> "Should use multiple-child days";
        };

        for (var antallBarn : new int[] {1, 2}) {
            settAntallBarnOgAnnenForeldersRett(data, antallBarn, true);
            var innhold = compileContent(mal, språk, data);
            assertThat(innhold).contains(flerbarnsdager);
            assertThat(innhold).contains(samtidigUttak).doesNotContain(samtidigUttakNei);
        }
    }

    @ParameterizedTest
    @MethodSource("utsettelser")
    void alle_utsettelsesårsaker_vises(BrevMal mal, Språk språk, String årsak, String forventet) {
        var data = data(mal);
        data.put("uttaksplan", Map.of("perioder", List.of(Map.of("fom", "2027-07-01", "tom", "2027-07-31",
            "søker", Map.of("forelder", "FAR_MEDMOR", "utsettelseÅrsak", årsak, "flerbarnsdager", false)))));

        assertThat(compileContent(mal, språk, data)).contains(forventet, "01.07.2027", "31.07.2027");
    }

    @ParameterizedTest
    @MethodSource("søknader")
    void gradering_og_overføring_vises_fra_nye_felter(BrevMal mal, Språk språk) {
        var data = data(mal);
        data.put("rolle", "MEDMOR");
        data.put("dekningsgrad", "100");
        data.put("uttaksplan", getJsonMapFromString("""
            {"perioder": [
              {"fom": "2027-07-01", "tom": "2027-07-31",
               "søker": {"forelder": "FAR_MEDMOR", "kontoType": "FEDREKVOTE", "flerbarnsdager": false,
                 "gradering": {"arbeidstidprosent": 20, "aktivitet": {"type": "FRILANS"}}}},
              {"fom": "2027-08-01", "tom": "2027-08-31",
               "søker": {"forelder": "FAR_MEDMOR", "kontoType": "MØDREKVOTE", "flerbarnsdager": false,
                 "overføringÅrsak": "SYKDOM_ANNEN_FORELDER",
                 "gradering": {"arbeidstidprosent": 30, "aktivitet": {"type": "SELVSTENDIG_NÆRINGSDRIVENDE"}}}},
              {"fom": "2027-09-01", "tom": "2027-09-30",
               "søker": {"forelder": "FAR_MEDMOR", "kontoType": "FORELDREPENGER", "flerbarnsdager": false,
                 "morsAktivitet": "IKKE_OPPGITT", "samtidigUttak": null,
                 "gradering": {"arbeidstidprosent": 0, "aktivitet": {"type": "ANNET"}}}}
            ]}
            """));

        var innhold = compileContent(mal, språk, data);

        switch (språk) {
            case BOKMÅL -> assertThat(innhold).contains("100 prosent", "Medmorkvote", "Jobber som: Frilanser",
                "Jobber som: Selvstendig næringsdrivende", "Jobber som: Annet", "Overføring av Mødrekvote",
                "Annen forelder er for syk til å ta seg av barnet", "Foreldrepenger uten aktivitetskrav", "Oppgitt prosent i arbeid: 0");
            case NYNORSK -> assertThat(innhold).contains("100 prosent", "Medmorkvote", "Jobbar som: Frilansar",
                "Jobbar som: Sjølvstendig næringsdrivande", "Jobbar som: Anna", "Overføring av Mødrekvote",
                "Annan forelder er for sjuk til å ta seg av barnet", "Foreldrepengar utan aktivitetskrav", "Oppgitt prosent i arbeid: 0");
            case ENGELSK -> assertThat(innhold).contains("100 per cent", "Co-mother quota", "Working as: Freelancer",
                "Working as: Self-employed", "Working as: Other", "Transfer of Maternal quota",
                "Other parent is too sick to take care of the child", "Parental benefit without activity requirement",
                "Specified employment percentage: 0");
        }
    }

    @ParameterizedTest
    @MethodSource("søknaderMedDekningsgradOgJustering")
    void ny_plan_kan_genereres_som_pdf(BrevMal mal, Språk språk, String dekningsgrad, boolean justertUttak) throws IOException {
        var data = data(mal);
        data.put("uttaksplan", getJsonMapFromString(NY_PLAN));
        data.put("dekningsgrad", dekningsgrad);
        plan(data).put("ønskerJustertUttakVedFødsel", justertUttak);
        try (var container = new Weld().disableDiscovery()
            .addBeanClasses(DokumentGeneratorTjeneste.class, JsonSchemaTjeneste.class, HandlebarsTjeneste.class, PdfGeneratorTjeneste.class)
            .initialize()) {
            var generator = container.select(DokumentGeneratorTjeneste.class).get();
            var pdf = generator.byggPdf(mal.getNavn(), JacksonUtil.JSON_MAPPER.writeValueAsString(data),
                DokumentSpråk.valueOf(språk.name()), DokumentCssStyling.FOR_PDF);
            try (var dokument = Loader.loadPDF(pdf)) {
                var innhold = new PDFTextStripper().getText(dokument).replaceAll("\\s+", " ");
                assertThat(innhold)
                    .contains("01.03.2027", "01.04.2027", "01.05.2027", "01.06.2027", "Testbedrift", "25.5", "50.5")
                    .contains(dekningsgrad + (språk == Språk.ENGELSK ? " per cent" : " prosent"))
                    .doesNotContain("01.01.2027", "01.02.2027", "<li>", "</li>");
                var justeringstekst = switch (språk) {
                    case BOKMÅL -> "Perioden som starter på termin blir endret til å starte fra fødselsdato når barnet blir født: Ja";
                    case NYNORSK -> "Perioden som startar på termin vert endra til å starte frå fødselsdato når barnet blir født: Ja";
                    case ENGELSK -> "The period starting at the due date will be changed to start from the birth date when the child is born: Yes";
                };
                if (justertUttak) {
                    assertThat(innhold).contains(justeringstekst);
                } else {
                    assertThat(innhold).doesNotContain(justeringstekst);
                }
            }
        }
    }

    private static Stream<Arguments> søknader() {
        return Stream.of(BrevMal.FORELDREPENGER_SØKNAD, BrevMal.FORELDREPNGER_ENDRING_SØKNAD)
            .flatMap(mal -> Arrays.stream(Språk.values()).map(språk -> Arguments.of(mal, språk)));
    }

    private static Stream<Arguments> søknaderMedDekningsgradOgJustering() {
        return søknader().flatMap(søknad -> Stream.of("80", "100")
            .flatMap(dekningsgrad -> Stream.of(true, false)
                .map(justering -> Arguments.of(søknad.get()[0], søknad.get()[1], dekningsgrad, justering))));
    }

    private static Stream<Arguments> utsettelser() {
        var årsaker = List.of(
            List.of("ARBEID", "Årsak til utsettelse: Arbeid", "Årsak til utsetjing: Arbeid", "Reason for postponement: Work"),
            List.of("FERIE", "Årsak til utsettelse: Ferie", "Årsak til utsetjing: Ferie", "Reason for postponement: Holiday"),
            List.of("FRI", "Periode uten uttak", "Periode utan uttak", "Period without withdrawal"),
            List.of("SØKER_SYKDOM", "Jeg er for syk", "Eg er for sjuk", "I am too sick"),
            List.of("SØKER_INNLAGT", "Jeg er innlagt", "Eg er innlagt", "I am admitted"),
            List.of("BARN_INNLAGT", "Barnet er innlagt", "Barnet er innlagt", "The child is admitted"),
            List.of("HV_ØVELSE", "Øvelse i Heimevernet", "Øving i Heimevernet", "Exercise in the Home Guard"),
            List.of("NAV_TILTAK", "Tiltakspenger Nav", "Tiltakspengar Nav", "Allowance from Nav"));
        return søknader().flatMap(søknad -> årsaker.stream().map(årsak -> {
            var språk = (Språk) søknad.get()[1];
            var indeks = switch (språk) {
                case BOKMÅL -> 1;
                case NYNORSK -> 2;
                case ENGELSK -> 3;
            };
            return Arguments.of(søknad.get()[0], språk, årsak.getFirst(), årsak.get(indeks));
        }));
    }

    private static Map<String, Object> data(BrevMal mal) {
        return new HashMap<>(getTestDataJson(mal, "",
            mal == BrevMal.FORELDREPENGER_SØKNAD ? "mor-1-AF-fødsel" : "endring-bfhr"));
    }

    @SuppressWarnings("unchecked")
    private static void settAntallBarnOgAnnenForeldersRett(Map<String, Object> data, int antallBarn, boolean harRett) {
        var barn = new HashMap<>((Map<String, Object>) data.get("barn"));
        barn.put("antallBarn", antallBarn);
        data.put("barn", barn);
        var annenForelder = new HashMap<>((Map<String, Object>) data.get("annenForelder"));
        var rettigheter = new HashMap<>((Map<String, Object>) annenForelder.get("rettigheter"));
        rettigheter.put("harRettPåForeldrepenger", harRett);
        annenForelder.put("rettigheter", rettigheter);
        data.put("annenForelder", annenForelder);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> plan(Map<String, Object> data) {
        return (Map<String, Object>) data.get("uttaksplan");
    }
}
