package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.IOException;
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
    class TableHelperTest {

        @Test
        void skalLageTabellMedToKolonner() throws IOException {
            var template = handlebars.compileInline("{{#table columns=2}}<td>A</td><td>B</td><td>C</td><td>D</td>{{/table}}");
            var result = template.apply(null);
            assertThat(result).contains("<table")
                .contains("<tr>")
                .contains("</tr>")
                .contains("<td>A</td>")
                .contains("<td>B</td>")
                .contains("<td>C</td>")
                .contains("<td>D</td>");
        }

        @Test
        void skalFylleUtTommeCellerVedUjevntAntall() throws IOException {
            var template = handlebars.compileInline("{{#table columns=2}}<td>A</td><td>B</td><td>C</td>{{/table}}");
            var result = template.apply(null);
            assertThat(result).contains("<td></td>");
        }

        @Test
        void skalLeggesTilClassAttributt() throws IOException {
            var template = handlebars.compileInline("{{#table columns=2 class=\"min-tabell\"}}<td>A</td>{{/table}}");
            var result = template.apply(null);
            assertThat(result).contains("class=min-tabell");
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
    class ConcatHelperTest {

        @Test
        void skalSetteSammenToStrenger() throws IOException {
            var template = handlebars.compileInline("{{concat \"header_\" \"nb\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("header_nb");
        }

        @Test
        void skalSetteSammenFlereStrenger() throws IOException {
            var template = handlebars.compileInline("{{concat \"a\" \"b\" \"c\" \"d\"}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("abcd");
        }

        @Test
        void skalBrukeKontekstVerdi() throws IOException {
            var template = handlebars.compileInline("{{concat \"header_\" språkKode}}");
            var result = template.apply(Map.of("språkKode", "nn"));
            assertThat(result).isEqualTo("header_nn");
        }

        @Test
        void skalIgnorereNullParametre() throws IOException {
            var template = handlebars.compileInline("{{concat \"prefix-\" mangler}}");
            var result = template.apply(Map.of());
            assertThat(result).isEqualTo("prefix-");
        }

        @Test
        void skalHåndtereTall() throws IOException {
            var template = handlebars.compileInline("{{concat \"v\" versjon}}");
            var result = template.apply(Map.of("versjon", 42));
            assertThat(result).isEqualTo("v42");
        }
    }

    @Nested
    class TranslateHelperTest {

        private Map<String, Object> kontekstMed(Map<String, Object> extras) {
            var map = new java.util.HashMap<String, Object>();
            map.put("__malNavn", "test-i18n");
            map.put("__språkKode", "nb");
            map.putAll(extras);
            return map;
        }

        @Test
        void skalSlåOppEnkelNøkkel() throws IOException {
            var template = handlebars.compileInline("{{t \"enkel\"}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Hei verden");
        }

        @Test
        void skalSubstituereNamedParameter() throws IOException {
            var template = handlebars.compileInline("{{t \"medParameter\" navn=\"Dolly\"}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Hei Dolly");
        }

        @Test
        void skalSubstituereFlereParametre() throws IOException {
            var template = handlebars.compileInline("{{t \"medFlereParametre\" hilsen=\"Hei\" navn=\"Dolly\" alder=30}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Hei Dolly, du er 30 år");
        }

        @Test
        void skalVelgeEntallVedBinaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"binaerPlural\" antall=1}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har en sak");
        }

        @Test
        void skalVelgeFlertallVedBinaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"binaerPlural\" antall=5}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har flere saker");
        }

        @Test
        void skalVelgeFlertallVedNullForBinaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"binaerPlural\" antall=0}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har flere saker");
        }

        @Test
        void skalVelgeZeroFormVedTernaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"ternaerPlural\" antall=0}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har ingen barn");
        }

        @Test
        void skalVelgeEntallVedTernaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"ternaerPlural\" antall=1}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har ett barn");
        }

        @Test
        void skalVelgeFlertallVedTernaerPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"ternaerPlural\" antall=3}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har flere barn");
        }

        @Test
        void skalKombinereSubstitusjonOgPluralForSammeVariabel() throws IOException {
            var binding = handlebars.compileInline("{{t \"pluralMedTekst\" antall=1}}");
            assertThat(binding.apply(kontekstMed(Map.of()))).isEqualTo("1 dag");
            var binding2 = handlebars.compileInline("{{t \"pluralMedTekst\" antall=7}}");
            assertThat(binding2.apply(kontekstMed(Map.of()))).isEqualTo("7 dager");
        }

        @Test
        void skalParseStringverdiSomTallForPlural() throws IOException {
            var template = handlebars.compileInline("{{t \"binaerPlural\" antall=\"1\"}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Du har en sak");
        }

        @Test
        void skalSubstituereTomtNårParameterMangler() throws IOException {
            var template = handlebars.compileInline("{{t \"manglerParameter\"}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Hei ");
        }

        @Test
        void skalRendreHtmlNårTripleStashBrukes() throws IOException {
            var template = handlebars.compileInline("{{{t \"medHtml\"}}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).isEqualTo("Linje 1<br/>Linje 2");
        }

        @Test
        void skalEskapereHtmlMedDoubleStash() throws IOException {
            var template = handlebars.compileInline("{{t \"medHtml\"}}");
            var result = template.apply(kontekstMed(Map.of()));
            assertThat(result).contains("&lt;br/&gt;");
        }

        @Test
        void skalFeileNårMalNavnManglerIKontekst() throws IOException {
            var template = handlebars.compileInline("{{t \"enkel\"}}");
            var kontekstUtenMalNavn = Map.<String, Object>of("__språkKode", "nb");
            assertThat(catchThrowable(() -> renderTemplate(template, kontekstUtenMalNavn)))
                .isInstanceOf(com.github.jknack.handlebars.HandlebarsException.class)
                .hasMessageContaining("__malNavn");
        }

        @Test
        void skalFeileNårBundleResourceManglerForSpråkKode() throws IOException {
            var template = handlebars.compileInline("{{t \"enkel\"}}");
            var kontekstMedUkjentSpråk = Map.<String, Object>of(
                "__malNavn", "test-i18n",
                "__språkKode", "fr");
            assertThat(catchThrowable(() -> renderTemplate(template, kontekstMedUkjentSpråk)))
                .isInstanceOf(com.github.jknack.handlebars.HandlebarsException.class)
                .hasMessageContaining("Missing i18n bundle");
        }

        @Test
        void skalFeileNårI18nNøkkelManglerIBundle() throws IOException {
            var template = handlebars.compileInline("{{t \"finnesIkke\"}}");
            assertThat(catchThrowable(() -> renderTemplate(template, kontekstMed(Map.of()))))
                .isInstanceOf(com.github.jknack.handlebars.HandlebarsException.class)
                .hasMessageContaining("Missing i18n key 'finnesIkke'");
        }

        private String renderTemplate(com.github.jknack.handlebars.Template template, Map<String, Object> kontekst) throws IOException {
            return template.apply(kontekst);
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


}
