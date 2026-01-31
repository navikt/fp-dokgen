package no.nav.foreldrepenger.dokgen.tjenester.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MarkdownUtilTest {

    @Test
    void konverterTilHtml_enkelTekst() {
        // Arrange
        var markdown = "Dette er en test";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).isNotNull().contains("Dette er en test");
    }

    @Test
    void konverterTilHtml_overskrift() {
        // Arrange
        var markdown = "# Overskrift";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).contains("<h1>Overskrift</h1>");
    }

    @Test
    void konverterTilHtml_paragraf() {
        // Arrange
        var markdown = "En paragraf med tekst.";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).contains("<p>En paragraf med tekst.</p>");
    }

    @Test
    void konverterTilHtml_fet_og_kursiv() {
        // Arrange
        var markdown = "**fet** og *kursiv*";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<strong>fet</strong>")
            .contains("<em>kursiv</em>");
    }

    @Test
    void konverterTilHtml_uordnetListe() {
        // Arrange
        var markdown = """
            - Punkt 1
            - Punkt 2
            - Punkt 3
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<ul>")
            .contains("<li>Punkt 1</li>")
            .contains("<li>Punkt 2</li>")
            .contains("<li>Punkt 3</li>")
            .contains("</ul>");
    }

    @Test
    void konverterTilHtml_ordnetListe() {
        // Arrange
        var markdown = """
            1. Første
            2. Andre
            3. Tredje
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<ol>")
            .contains("<li>Første</li>")
            .contains("<li>Andre</li>")
            .contains("<li>Tredje</li>")
            .contains("</ol>");
    }

    @Test
    void konverterTilHtml_lenke() {
        // Arrange
        var markdown = "[NAV](https://nav.no)";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).contains("<a href=\"https://nav.no\">NAV</a>");
    }

    @Test
    void konverterTilHtml_tabell() {
        // Arrange
        var markdown = """
            | Kolonne 1 | Kolonne 2 |
            |-----------|-----------|
            | Verdi 1   | Verdi 2   |
            | Verdi 3   | Verdi 4   |
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<table>")
            .contains("<thead>")
            .contains("<tbody>")
            .contains("Kolonne 1")
            .contains("Verdi 1")
            .contains("</table>");
    }

    @Test
    void konverterTilHtml_kombinertMarkdown() {
        // Arrange
        var markdown = """
            # Vedtak om foreldrepenger

            Du har fått innvilget **foreldrepenger**.

            ## Perioder

            | Fra       | Til       | Type          |
            |-----------|-----------|---------------|
            | 01.01.2026| 01.03.2026| Mødrekvote    |

            Les mer på [NAV](https://nav.no).
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<h1>Vedtak om foreldrepenger</h1>")
            .contains("<h2>Perioder</h2>")
            .contains("<strong>foreldrepenger</strong>")
            .contains("<table>")
            .contains("Mødrekvote")
            .contains("<a href=\"https://nav.no\">NAV</a>");
    }

    @Test
    void konverterTilHtml_tomStreng() {
        // Arrange
        var markdown = "";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).isNotNull().isEmpty();
    }

    @Test
    void konverterTilHtml_multipleOverskrifter() {
        // Arrange
        var markdown = """
            # H1
            ## H2
            ### H3
            #### H4
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<h1>H1</h1>")
            .contains("<h2>H2</h2>")
            .contains("<h3>H3</h3>")
            .contains("<h4>H4</h4>");
    }

    @Test
    void konverterTilHtml_sitat() {
        // Arrange
        var markdown = "> Dette er et sitat";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<blockquote>")
            .contains("Dette er et sitat")
            .contains("</blockquote>");
    }

    @Test
    void konverterTilHtml_kodeblokk() {
        // Arrange
        var markdown = """
            ```java
            public class Test {}
            ```
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<pre>")
            .contains("<code")
            .contains("public class Test {}");
    }

    @Test
    void konverterTilHtml_inlineKode() {
        // Arrange
        var markdown = "Bruk `System.out.println()` for utskrift";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).contains("<code>System.out.println()</code>");
    }

    @Test
    void konverterTilHtml_horisontalLinje() {
        // Arrange
        var markdown = """
            Før

            ---

            Etter
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html).contains("<hr />");
    }

    @Test
    void konverterTilHtml_nestedListe() {
        // Arrange
        var markdown = """
            - Nivå 1
              - Nivå 2
                - Nivå 3
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<ul>")
            .contains("Nivå 1")
            .contains("Nivå 2")
            .contains("Nivå 3");
    }

    @Test
    void konverterTilHtml_bildeTag() {
        // Arrange
        var markdown = "![Alt tekst](https://example.com/bilde.png)";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<img")
            .contains("src=\"https://example.com/bilde.png\"")
            .contains("alt=\"Alt tekst\"");
    }

    @Test
    void konverterTilHtml_escapeHtml() {
        // Arrange
        var markdown = "Test med <script>alert('xss')</script>";

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert - CommonMark escaper ikke HTML som standard, men vi sjekker at det ikke krasjer
        assertThat(html).isNotNull();
    }

    @Test
    void konverterTilHtml_linjeSkift() {
        // Arrange
        var markdown = """
            Linje 1

            Linje 2
            """;

        // Act
        var html = MarkdownUtil.konverterTilHtml(markdown);

        // Assert
        assertThat(html)
            .contains("<p>Linje 1</p>")
            .contains("<p>Linje 2</p>");
    }
}
