package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.github.jknack.handlebars.Handlebars;

import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil;
import tools.jackson.databind.json.JsonMapper;

class HandlebarsCustomHelpersTest {

    private static final JsonMapper JSON_MAPPER = JacksonUtil.JSON_MAPPER;

    private Handlebars handlebars;

    @BeforeEach
    void setUp() {
        handlebars = new HandlebarsTjeneste().getHandlebars();
    }

    @Nested
    class AdditionHelperTest {

        @Test
        void skalLeggeSammenToTall() throws IOException {
            var template = handlebars.compileInline("{{add 3 4}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("7");
        }

        @Test
        void skalLeggeSammenNegativeTall() throws IOException {
            var template = handlebars.compileInline("{{add -5 10}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("5");
        }

        @Test
        void skalLeggeSammenMedNull() throws IOException {
            var template = handlebars.compileInline("{{add 0 100}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("100");
        }
    }

    @Nested
    class NorwegianDateHelperTest {

        @Test
        void skalFormatereDatoTilNorskFormat() throws IOException {
            var template = handlebars.compileInline("{{norwegian-date \"2026-01-29\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("29.01.2026");
        }

        @Test
        void skalFormatereDatoMedEnSifretDagOgMåned() throws IOException {
            var template = handlebars.compileInline("{{norwegian-date \"2026-05-09\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("09.05.2026");
        }
    }

    @Nested
    class NorwegianDateTimeHelperTest {

        @Test
        void skalFormatereDatoTidTilNorskFormat() throws IOException {
            var template = handlebars.compileInline("{{norwegian-datetime \"2019-08-19T15:54:01\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("19.08.2019 15:54");
        }

        @Test
        void skalInkludereSekunderNårSpesifisert() throws IOException {
            var template = handlebars.compileInline("{{norwegian-datetime \"2019-08-19T15:54:01\" includeSeconds=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("19.08.2019 15:54:01");
        }
    }

    @Nested
    class DivideHelperTest {

        @Test
        void skalDividereOgRundeOpp() throws IOException {
            var template = handlebars.compileInline("{{divide 3 10}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("3");
        }

        @Test
        void skalDividereOgRundeNed() throws IOException {
            var template = handlebars.compileInline("{{divide 3 7}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("2");
        }

        @Test
        void skalDividereJevnt() throws IOException {
            var template = handlebars.compileInline("{{divide 2 100}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("50");
        }
    }

    @Nested
    class ThousandSeperatorHelperTest {

        private static final String NBSP = "\u00A0"; // Non-breaking space used by Norwegian locale

        @Test
        void skalFormatereMedTusenSkilletegn() throws IOException {
            var context = Map.of("tall", 10000);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("10" + NBSP + "000");
        }

        @Test
        void skalIkkeEndreSmåTall() throws IOException {
            var context = Map.of("tall", 999);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("999");
        }

        @Test
        void skalHåndtereStoreTall() throws IOException {
            var context = Map.of("tall", 1234567);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("1" + NBSP + "234" + NBSP + "567");
        }

        @Test
        void skalHåndtereNull() throws IOException {
            var context = Map.of("tall", 0);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("0");
        }

        @Test
        void skalFormatereMedNorskTusenSkilletegn_1000() throws IOException {
            var context = Map.of("tall", 1000);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            // Norwegian locale uses non-breaking space as thousand separator
            assertThat(result).isEqualTo("1" + NBSP + "000");
        }

        @Test
        void skalFormatereMedNorskTusenSkilletegn_millioner() throws IOException {
            var context = Map.of("tall", 1000000);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("1" + NBSP + "000" + NBSP + "000");
        }

        @Test
        void skalFormatereDesimalMedKomma() throws IOException {
            var context = Map.of("tall", 20000.45);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            // Norwegian locale uses comma as decimal separator and non-breaking space as thousand separator
            assertThat(result).isEqualTo("20" + NBSP + "000,45");
        }

        @Test
        void skalFormatereDesimalUtenUnnødigeDesimaler() throws IOException {
            var context = Map.of("tall", 1000.0);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("1" + NBSP + "000");
        }

        @Test
        void skalFormatereDesimalMedEnDesimal() throws IOException {
            var context = Map.of("tall", 99.9);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("99,9");
        }

        @Test
        void skalRundeAvTilToDesimaler() throws IOException {
            var context = Map.of("tall", 1234.5678);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("1" + NBSP + "234,57");
        }

        @Test
        void skalHåndtereNegativeTall() throws IOException {
            var context = Map.of("tall", -5000);
            var template = handlebars.compileInline("{{thousand-seperator tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("-5" + NBSP + "000");
        }
    }

    @Nested
    class TrimDecimalHelperTest {

        @Test
        void skalFjerneTrailingNuller() throws IOException {
            var template = handlebars.compileInline("{{trim-decimal 10.0}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("10");
        }

        @Test
        void skalBeholdeDesimaLerSomIkkeErNull() throws IOException {
            var template = handlebars.compileInline("{{trim-decimal 100.3}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("100.3");
        }

        @Test
        void skalFjerneTrailingNullEtterDesimal() throws IOException {
            var template = handlebars.compileInline("{{trim-decimal 90.20}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("90.2");
        }

        @Test
        void skalHåndtereDoubleFraKontekst() throws IOException {
            var context = Map.of("tall", 25.50);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("25.5");
        }

        @Test
        void skalHåndtereDoubleUtenDesimaler() throws IOException {
            var context = Map.of("tall", 100.00);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("100");
        }

        @Test
        void skalHåndtereIntegerUendret() throws IOException {
            var context = Map.of("tall", 42);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("42");
        }

        @Test
        void skalHåndtereNegativeDoubleTall() throws IOException {
            var context = Map.of("tall", -15.0);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("-15");
        }

        @Test
        void skalHåndtereNegativeIntegerTall() throws IOException {
            var context = Map.of("tall", -100);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("-100");
        }

        @Test
        void skalHåndtereStorDoubleMedFlereDesimaLer() throws IOException {
            var context = Map.of("tall", 12345.6789);
            var template = handlebars.compileInline("{{trim-decimal tall}}");
            var result = template.apply(context);
            assertThat(result).isEqualTo("12345.6789");
        }
    }

    @Nested
    class CountryCodeHelperTest {

        @Test
        void skalKonvertereAlpha2TilNorskLandnavn() throws IOException {
            var template = handlebars.compileInline("{{land-norsk \"NO\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("Norge");
        }

        @Test
        void skalKonvertereAlpha3TilNorskLandnavn() throws IOException {
            var template = handlebars.compileInline("{{land-norsk \"NOR\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("Norge");
        }

        @Test
        void skalKonvertereTilEngelskNårSpesifisert() throws IOException {
            var template = handlebars.compileInline("{{land-norsk \"NO\" lang=\"en\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("Norway");
        }

        @Test
        void skalReturnereBlankForTomInput() throws IOException {
            var template = handlebars.compileInline("{{land-norsk \"\"}}");
            var result = template.apply(null);
            assertThat(result).isEmpty();
        }
    }

    @Nested
    class AntallVirkedagerMellomToDatoerTest {

        @Test
        void skalTelleVirkedagerIEnUke() throws IOException {
            // Mandag 2026-01-05 til Fredag 2026-01-09 = 5 virkedager
            var template = handlebars.compileInline("{{antall-virkedager \"2026-01-05\" \"2026-01-09\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("5");
        }

        @Test
        void skalEkskludereHelg() throws IOException {
            // Fredag 2026-01-09 til Mandag 2026-01-12 = 2 virkedager (fredag og mandag)
            var template = handlebars.compileInline("{{antall-virkedager \"2026-01-09\" \"2026-01-12\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("2");
        }

        @Test
        void skalHåndtereOmvendtRekkefølge() throws IOException {
            var template = handlebars.compileInline("{{antall-virkedager \"2026-01-09\" \"2026-01-05\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("5");
        }

        @Test
        void skalReturnereEnForSammeDag() throws IOException {
            var template = handlebars.compileInline("{{antall-virkedager \"2026-01-06\" \"2026-01-06\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("1");
        }

        @Test
        void skalReturnereNullForHelgedag() throws IOException {
            // Lørdag til lørdag
            var template = handlebars.compileInline("{{antall-virkedager \"2026-01-10\" \"2026-01-10\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("0");
        }
    }

    @Nested
    class FormatTextHelperTest {

        @Test
        void skalKonvertereTilSmåBokstaver() throws IOException {
            var template = handlebars.compileInline("{{format-text \"HELLO\" toLower=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("hello");
        }

        @Test
        void skalKonvertereTilStoreBokstaver() throws IOException {
            var template = handlebars.compileInline("{{format-text \"hello\" toUpper=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("HELLO");
        }

        @Test
        void skalErstatteUnderscore() throws IOException {
            var template = handlebars.compileInline("{{format-text \"HELLO_WORLD\" replaceUnderscore=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("HELLO WORLD");
        }

        @Test
        void skalStorFørsteBokstav() throws IOException {
            var template = handlebars.compileInline("{{format-text \"hello world\" capitalizeFirst=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("Hello world");
        }

        @Test
        void skalKombinereFlereAlternativer() throws IOException {
            var template = handlebars.compileInline("{{format-text \"HELLO_WORLD\" toLower=true replaceUnderscore=true capitalizeFirst=true}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("Hello world");
        }

        @Test
        void skalReturnereBlankForNull() throws IOException {
            var template = handlebars.compileInline("{{format-text null}}");
            var result = template.apply(null);
            assertThat(result).isEmpty();
        }
    }

    @Nested
    class InArrayHelperTest {

        @Test
        void skalReturnereBlockNårVerdiFinnesiArray() throws IOException {
            var template = handlebars.compileInline("{{#in-array (array \"a\" \"b\" \"c\") \"b\"}}funnet{{else}}ikke funnet{{/in-array}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("funnet");
        }

        @Test
        void skalReturnereElseBlockNårVerdiIkkeFinnes() throws IOException {
            var template = handlebars.compileInline("{{#in-array (array \"a\" \"b\" \"c\") \"d\"}}funnet{{else}}ikke funnet{{/in-array}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("ikke funnet");
        }
    }

    @Nested
    class SwitchCaseHelperTest {

        @Test
        void skalVelgeRiktigCase() throws IOException {
            var template = handlebars.compileInline("""
                {{#switch status}}
                    {{#case "AKTIV"}}Aktiv bruker{{/case}}
                    {{#case "INAKTIV"}}Inaktiv bruker{{/case}}
                {{/switch}}
                """);
            var result = template.apply(java.util.Map.of("status", "AKTIV"));
            assertThat(result.trim()).isEqualTo("Aktiv bruker");
        }

        @Test
        void skalVelgeAnnenCase() throws IOException {
            var template = handlebars.compileInline("""
                {{#switch status}}
                    {{#case "AKTIV"}}Aktiv bruker{{/case}}
                    {{#case "INAKTIV"}}Inaktiv bruker{{/case}}
                {{/switch}}
                """);
            var result = template.apply(java.util.Map.of("status", "INAKTIV"));
            assertThat(result.trim()).isEqualTo("Inaktiv bruker");
        }

        @Test
        void skalMatcheCaseMotArrayAvKonstanter() throws IOException {
            var template = handlebars.compileInline("""
                {{#switch status}}
                    {{#case (array "AKTIV" "PENDING")}}Treff{{/case}}
                    {{#case "INAKTIV"}}Bom{{/case}}
                {{/switch}}
                """);
            var result = template.apply(java.util.Map.of("status", "PENDING"));
            assertThat(result.trim()).isEqualTo("Treff");
        }


        @Test
        void skalStøtteNestedeHjelpereInniCase() throws IOException {
            var template = handlebars.compileInline("""
                {{#switch avslagsårsak}}
                    {{#case (array "1027" "1029")}}
                        {{#in-array (array "FARA" "MMOR") relasjonsRolleType}}
                            Du er {{#eq relasjonsRolleType "MMOR"}}medmor{{/eq}}{{#eq relasjonsRolleType "FARA"}}far{{/eq}}
                        {{/in-array}}
                    {{/case}}
                {{/switch}}
                """);
            var result = template.apply(java.util.Map.of("avslagsårsak", "1027", "relasjonsRolleType", "MMOR"));
            assertThat(result).contains("Du er medmor");
        }
    }

    @Nested
    class SizeHelperTest {

        @Test
        void skalReturnereStørrelseAvArray() throws IOException {
            var template = handlebars.compileInline("{{size items}}");
            var result = template.apply(
                java.util.Map.of("items", JSON_MAPPER.createArrayNode().add("a").add("b").add("c")));
            assertThat(result).isEqualTo("3");
        }

        @Test
        void skalReturnereNullForTomtArray() throws IOException {
            var template = handlebars.compileInline("{{size items}}");
            var result = template.apply(java.util.Map.of("items", JSON_MAPPER.createArrayNode()));
            assertThat(result).isEqualTo("0");
        }

        @Test
        void skalReturnereNullForIkkeArray() throws IOException {
            var template = handlebars.compileInline("{{size items}}");
            var result = template.apply(java.util.Map.of("items", "not an array"));
            assertThat(result).isEqualTo("0");
        }
    }

    @Nested
    class NæringForelagtHelperTest {

        @Test
        void skalFinneNæringMedSammeOrganisasjonsnummer() throws IOException {
            var template = handlebars.compileInline(
                    "{{#if (næring-forelagt organisasjonsnummer næringer)}}forelagt{{else}}ikke forelagt{{/if}}");
            var context = Map.of(
                    "organisasjonsnummer", "999999999",
                    "næringer", List.of(Map.of("organisasjonsnummer", "999999999")));

            assertThat(template.apply(context)).isEqualTo("forelagt");
        }

        @Test
        void skalIkkeMatcheEnAnnenNæring() throws IOException {
            var template = handlebars.compileInline(
                    "{{#if (næring-forelagt organisasjonsnummer næringer)}}forelagt{{else}}ikke forelagt{{/if}}");
            var context = Map.of(
                    "organisasjonsnummer", "999999999",
                    "næringer", List.of(Map.of("organisasjonsnummer", "974760673")));

            assertThat(template.apply(context)).isEqualTo("ikke forelagt");
        }
    }

    @Nested
    class GtWithSizeHelperTest {

        @Test
        void skalReturnereBlockNårArrayHarMerEnnEttElement() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var jsonNodes = JSON_MAPPER.createObjectNode().put("test", "value").put("test2", "value2");
            var result = template.apply(java.util.Map.of("avvistGrunner", JSON_MAPPER.createArrayNode().add(jsonNodes).add(jsonNodes).add(jsonNodes)));
            assertThat(result).isEqualTo("flere");
        }

        @Test
        void skalReturnereElseBlockNårArrayHarEttElement() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var result = template.apply(
                java.util.Map.of("avvistGrunner", JSON_MAPPER.createArrayNode().add("grunn1")));
            assertThat(result).isEqualTo("en eller ingen");
        }

        @Test
        void skalReturnereElseBlockNårArrayErTomt() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var result = template.apply(java.util.Map.of("avvistGrunner", JSON_MAPPER.createArrayNode()));
            assertThat(result).isEqualTo("en eller ingen");
        }
    }

    @Nested
    class FinnesHelperTest {

        @Test
        void skalVæreUsannNårFeltetMangler() throws IOException {
            var template = handlebars.compileInline("{{#if (finnes søkerinfo.selvstendigNæring)}}ja{{else}}nei{{/if}}");
            assertThat(template.apply(Map.of("søkerinfo", Map.of()))).isEqualTo("nei");
        }

        @Test
        void skalVæreUsannForNullNode() throws IOException {
            var template = handlebars.compileInline("{{#if (finnes felt)}}ja{{else}}nei{{/if}}");
            var input = JSON_MAPPER.createObjectNode();
            input.putNull("felt");
            assertThat(template.apply(Map.of("felt", input.get("felt")))).isEqualTo("nei");
        }

        @Test
        void skalVæreSannForTomListeSelvOmIfAleneVilleVærtUsann() throws IOException {
            var template = handlebars.compileInline("{{#if (finnes felt)}}ja{{else}}nei{{/if}}|{{#if felt}}ja{{else}}nei{{/if}}");
            assertThat(template.apply(Map.of("felt", List.of()))).isEqualTo("ja|nei");
            var jsonInput = JSON_MAPPER.createObjectNode();
            jsonInput.putArray("felt");
            assertThat(template.apply(Map.of("felt", jsonInput.get("felt")))).isEqualTo("ja|nei");
        }

    }

    @Nested
    class AktiviteterForelagtHelperTest {

        private static final String MAL = "{{#if (aktiviteter-forelagt søkerinfo)}}ny{{else}}legacy{{/if}}";

        @Test
        void skalVæreLegacyNårBeggeFelteneMangler() throws IOException {
            var template = handlebars.compileInline(MAL);
            assertThat(template.apply(Map.of("søkerinfo", Map.of("arbeidsforhold", List.of())))).isEqualTo("legacy");
        }

        @Test
        void skalVæreLegacyNårSøkerinfoMangler() throws IOException {
            var template = handlebars.compileInline(MAL);
            assertThat(template.apply(Map.of())).isEqualTo("legacy");
        }

        @Test
        void skalVæreLegacyForNullNode() throws IOException {
            var template = handlebars.compileInline(MAL);
            var søkerinfo = JSON_MAPPER.createObjectNode();
            søkerinfo.putNull("selvstendigNæring");
            søkerinfo.putNull("frilansoppdrag");
            assertThat(template.apply(Map.of("søkerinfo", søkerinfo))).isEqualTo("legacy");
        }

        @Test
        void skalVæreNyNårFelteneErTomme() throws IOException {
            var template = handlebars.compileInline(MAL);
            assertThat(template.apply(Map.of("søkerinfo", Map.of("selvstendigNæring", List.of(), "frilansoppdrag", List.of()))))
                    .isEqualTo("ny");
            var søkerinfo = JSON_MAPPER.createObjectNode();
            søkerinfo.putArray("selvstendigNæring");
            søkerinfo.putArray("frilansoppdrag");
            assertThat(template.apply(Map.of("søkerinfo", søkerinfo))).isEqualTo("ny");
        }

        @Test
        void skalVæreNyNårKunEttAvFelteneFinnes() throws IOException {
            var template = handlebars.compileInline(MAL);
            assertThat(template.apply(Map.of("søkerinfo", Map.of("frilansoppdrag", List.of())))).isEqualTo("ny");
            assertThat(template.apply(Map.of("søkerinfo", Map.of("selvstendigNæring", List.of())))).isEqualTo("ny");
        }

        @Test
        void skalVæreNyNårFelteneHarInnhold() throws IOException {
            var template = handlebars.compileInline(MAL);
            assertThat(template.apply(Map.of("søkerinfo", Map.of("selvstendigNæring", List.of(Map.of("navn", "Fiske")))))).isEqualTo("ny");
        }
    }

    @Nested
    class GrupperFrilansoppdragHelperTest {

        private static final String MAL = """
            {{#each (grupper-frilansoppdrag oppdrag)}}
            {{navn}}={{antallOppdrag}}:{{fom}}-{{tom}}
            {{/each}}""";

        @Test
        void skalGrupperePåNavnOgBeregneYtterperioden() throws IOException {
            var template = handlebars.compileInline(MAL);
            var oppdrag = List.of(
                Map.of("navn", "Kulturskolen", "fom", "2025-02-01", "tom", "2025-02-28"),
                Map.of("navn", "Teaterlaget", "fom", "2025-04-01", "tom", "2025-04-30"),
                Map.of("navn", "Kulturskolen", "fom", "2025-01-01", "tom", "2025-03-31"));

            assertThat(template.apply(Map.of("oppdrag", oppdrag)))
                .containsSubsequence(
                    "Kulturskolen=2:2025-01-01-2025-03-31",
                    "Teaterlaget=1:2025-04-01-2025-04-30");
        }

        @Test
        void skalStøtteJsonInputOgViseGruppenSomPågåendeNårEnPeriodeErÅpen() throws IOException {
            var template = handlebars.compileInline(MAL);
            var oppdrag = JSON_MAPPER.createArrayNode();
            oppdrag.addObject().put("navn", "Kulturskolen").put("fom", "2025-02-01").put("tom", "2025-02-28");
            oppdrag.addObject().put("navn", "Kulturskolen").put("fom", "2025-01-01");

            assertThat(template.apply(Map.of("oppdrag", oppdrag)))
                .contains("Kulturskolen=2:2025-01-01-");
        }
    }

    @Nested
    class PunktlisteHelperTest {

        @Test
        void skalByggeVelformetListeOgDroppeTommePunkter() throws IOException {
            var template = handlebars.compileInline("""
                    {{#punktliste}}
                    {{#punkt}}Første{{/punkt}}
                    {{#punkt}}{{#if mangler}}Skal ikke vises{{/if}}{{/punkt}}
                    {{#punkt}}Andre: {{verdi}}{{/punkt}}
                    {{/punktliste}}""");
            var result = template.apply(Map.of("verdi", 42));
            assertThat(result).isEqualTo("<ul>\n    <li>Første</li>\n    <li>Andre: 42</li>\n</ul>");
        }

        @Test
        void skalIkkeRendreListeNårAllePunkterErTomme() throws IOException {
            var template = handlebars.compileInline("""
                    {{#punktliste}}
                    {{#punkt}}{{#if mangler}}Nei{{/if}}{{/punkt}}
                    {{#punkt}}   {{/punkt}}
                    {{/punktliste}}""");
            assertThat(template.apply(Map.of())).isEmpty();
        }

        @Test
        void skalVæreUpåvirketAvWhitespaceOgInaktiveGrener() throws IOException {
            var kompakt = handlebars.compileInline("{{#punktliste}}{{#punkt}}Ett{{/punkt}}{{#punkt}}To{{/punkt}}{{/punktliste}}");
            var luftig = handlebars.compileInline("""
                    {{#punktliste}}

                    {{#unless finnesIkke}}
                    {{#punkt}}Ett{{/punkt}}

                    {{/unless}}
                    {{#punkt}}To{{/punkt}}

                    {{/punktliste}}""");
            assertThat(luftig.apply(Map.of())).isEqualTo(kompakt.apply(Map.of()));
        }

        @Test
        void skalSamlePunkterFraEach() throws IOException {
            var template = handlebars.compileInline("{{#punktliste}}{{#each navn}}{{#punkt}}Navn: {{this}}{{/punkt}}{{/each}}{{/punktliste}}");
            var result = template.apply(Map.of("navn", List.of("Ola", "Kari")));
            assertThat(result).isEqualTo("<ul>\n    <li>Navn: Ola</li>\n    <li>Navn: Kari</li>\n</ul>");
        }

        @Test
        void skalEskapereHtmlIVerdierMenBevareMarkupFraMalen() throws IOException {
            var template = handlebars.compileInline("{{#punktliste}}{{#punkt}}Svar: <strong>{{verdi}}</strong>{{/punkt}}{{/punktliste}}");
            var result = template.apply(Map.of("verdi", "<script>"));
            assertThat(result).isEqualTo("<ul>\n    <li>Svar: <strong>&lt;script&gt;</strong></li>\n</ul>");
        }
    }

}
