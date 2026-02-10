package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.handlebars;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;

class HandlebarsCustomHelpersTest {

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

        @Test
        void skalFormatereMedTusenSkilletegn() throws IOException {
            var template = handlebars.compileInline("{{thousand-seperator 10000}}");
            var result = template.apply(null);
            assertThat(result).contains("10").contains("000");
        }

        @Test
        void skalIkkeEndreSmåTall() throws IOException {
            var template = handlebars.compileInline("{{thousand-seperator 999}}");
            var result = template.apply(null);
            assertThat(result).isEqualTo("999");
        }

        @Test
        void skalHåndtereStoreTall() throws IOException {
            var template = handlebars.compileInline("{{thousand-seperator 1234567}}");
            var result = template.apply(null);
            assertThat(result).contains("1").contains("234").contains("567");
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
                java.util.Map.of("items", new com.fasterxml.jackson.databind.ObjectMapper().createArrayNode().add("a").add("b").add("c")));
            assertThat(result).isEqualTo("3");
        }

        @Test
        void skalReturnereNullForTomtArray() throws IOException {
            var template = handlebars.compileInline("{{size items}}");
            var result = template.apply(java.util.Map.of("items", new com.fasterxml.jackson.databind.ObjectMapper().createArrayNode()));
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
    class GtWithSizeHelperTest {

        @Test
        void skalReturnereBlockNårArrayHarMerEnnEttElement() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var jsonNodes = new ObjectMapper().createObjectNode().put("test", "value").put("test2", "value2");
            var result = template.apply(java.util.Map.of("avvistGrunner",
                new com.fasterxml.jackson.databind.ObjectMapper().createArrayNode().add(jsonNodes).add(jsonNodes).add(jsonNodes)));
            assertThat(result).isEqualTo("flere");
        }

        @Test
        void skalReturnereElseBlockNårArrayHarEttElement() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var result = template.apply(
                java.util.Map.of("avvistGrunner", new com.fasterxml.jackson.databind.ObjectMapper().createArrayNode().add("grunn1")));
            assertThat(result).isEqualTo("en eller ingen");
        }

        @Test
        void skalReturnereElseBlockNårArrayErTomt() throws IOException {
            var template = handlebars.compileInline("{{~#gt (size avvistGrunner) 1}}flere{{else}}en eller ingen{{/gt}}");
            var result = template.apply(java.util.Map.of("avvistGrunner", new com.fasterxml.jackson.databind.ObjectMapper().createArrayNode()));
            assertThat(result).isEqualTo("en eller ingen");
        }
    }


}
