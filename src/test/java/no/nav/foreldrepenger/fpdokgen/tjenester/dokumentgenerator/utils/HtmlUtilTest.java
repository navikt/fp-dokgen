package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentCssStyling;

class HtmlUtilTest {

    @Nested
    class UtvidMedHtmlMetadataHeaderFooterTest {

        @Test
        void skalPakkeInnholdIContentDiv() {
            var innhold = "<p>Test innhold</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<div id=\"content\">")
                .contains("Test innhold")
                .contains("</div>");
        }

        @Test
        void skalLeggesTilMetaCharsetUtf8() {
            var innhold = "<p>Test</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html).contains("<meta charset=\"UTF-8\"");
        }

        @Test
        void skalLeggesTilCssStyle() {
            var innhold = "<p>Test</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<style>")
                .contains("</style>");
        }

        @Test
        void skalBeholdeHtmlStruktur() {
            var innhold = "<h1>Overskrift</h1><p>Paragraf</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<h1>Overskrift</h1>")
                .contains("<p>Paragraf</p>");
        }

        @Test
        void skalHandtereNorskeTegn() {
            var innhold = "<p>Søknad om foreldrepenger for Æøå Åsen</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("Søknad om foreldrepenger")
                .contains("Æøå Åsen");
        }

        @Test
        void skalHandtereTomtInnhold() {
            var innhold = "";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<div id=\"content\">")
                .contains("<meta charset=\"UTF-8\"");
        }

        @Test
        void skalHandtereKomplekstHtmlInnhold() {
            var innhold = """
                <h1>Vedtak om foreldrepenger</h1>
                <p>Du har fått innvilget <strong>foreldrepenger</strong>.</p>
                <table>
                    <tr><td>Fra</td><td>Til</td></tr>
                    <tr><td>01.01.2026</td><td>01.03.2026</td></tr>
                </table>
                <ul>
                    <li>Punkt 1</li>
                    <li>Punkt 2</li>
                </ul>
                """;

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<h1>Vedtak om foreldrepenger</h1>")
                .contains("<strong>foreldrepenger</strong>")
                .contains("<table>")
                .contains("<li>Punkt 1</li>")
                .contains("<li>Punkt 2</li>");
        }

        @Test
        void skalHaGyldigHtmlStruktur() {
            var innhold = "<p>Test</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<html>")
                .contains("<head>")
                .contains("<body>")
                .contains("</html>");
        }

        @Test
        void skalPlassereStyleIHead() {
            var innhold = "<p>Test</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            // Style skal være mellom <head> og </head>
            var headStart = html.indexOf("<head>");
            var headEnd = html.indexOf("</head>");
            var styleStart = html.indexOf("<style>");

            assertThat(styleStart)
                .isGreaterThan(headStart)
                .isLessThan(headEnd);
        }

        @Test
        void skalPlassereInnholdIBody() {
            var innhold = "<p>Test innhold</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            // Content div skal være mellom <body> og </body>
            var bodyStart = html.indexOf("<body>");
            var bodyEnd = html.indexOf("</body>");
            var contentStart = html.indexOf("<div id=\"content\">");

            assertThat(contentStart)
                .isGreaterThan(bodyStart)
                .isLessThan(bodyEnd);
        }

        @Test
        void skalHaDoctypeForOpenhtmltopdf() {
            var innhold = "<p>Test</p>";

            var html = HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innhold, DokumentCssStyling.FOR_PDF);

            assertThat(html)
                .contains("<!DOCTYPE html PUBLIC")
                .contains("OPENHTMLTOPDF");
        }
    }
}
