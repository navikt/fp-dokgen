package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.utils;

import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownUtil {

    private MarkdownUtil() {
        // Utility class.
    }

    private static final List<Extension> markdownExtensions = List.of(TablesExtension.create());
    private static final Parser markdownToHtmlParser = Parser.builder().extensions(markdownExtensions).build();
    private static final HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(markdownExtensions).build();

    public static String konverterTilHtml(String markdown) {
        return renderToHTML(parseDocument(markdown));
    }

    private static Node parseDocument(String content) {
        return markdownToHtmlParser.parse(content);
    }

    private static String renderToHTML(Node document) {
        return htmlRenderer.render(document);
    }
}
