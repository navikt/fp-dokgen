package no.nav.foreldrepenger.dokgen.tjenester.utils;

import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentCssPathFor;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentFooterPathFor;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentHeaderPathFor;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.lesRessursSomString;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;

public class HtmlUtil {

    private static final ConcurrentHashMap<String, String> HTML_CACHE = new ConcurrentHashMap<>();

    private HtmlUtil() {
    }

    public static String utvidMedHtmlMetadataHeaderFooter(String htmlInnhold, DokStyling styling) {
        //nødvendig doctype for å støtte non-breaking space i openhtmltopdf
        return """
            <!DOCTYPE html PUBLIC "-//OPENHTMLTOPDF//DOC XHTML Character Entities Only 1.0//EN" "">
            <html>
            <head>
                <meta charset="UTF-8" />
                <style>%s</style>
            </head>
            <body>
            %s
                <div id="content">%s</div>
            %s
            </body>
            </html>
            """.formatted(hentCss(styling), hentHeader(styling), htmlInnhold, hentFooter(styling));
    }

    private static String hentHeader(DokStyling styling) {
        var cacheKey = hentHeaderPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
    }

    private static String hentFooter(DokStyling styling) {
        var cacheKey = hentFooterPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
    }

    private static String hentCss(DokStyling styling) {
        var cacheKey = hentCssPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));

    }
}
