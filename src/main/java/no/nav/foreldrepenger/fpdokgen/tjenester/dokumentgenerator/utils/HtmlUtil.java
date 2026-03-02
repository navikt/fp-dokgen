package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils;

import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.hentCssPathFor;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.hentFooterPathFor;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.hentHeaderPathFor;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.lesRessursSomString;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentCssStyling;

public class HtmlUtil {

    private static final ConcurrentHashMap<String, String> HTML_CACHE = new ConcurrentHashMap<>();

    private HtmlUtil() {
        // Utility class.
    }

    public static String utvidMedHtmlMetadataHeaderFooter(String htmlInnhold, DokumentCssStyling styling) {
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

    private static String hentHeader(DokumentCssStyling styling) {
        var cacheKey = hentHeaderPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
    }

    private static String hentFooter(DokumentCssStyling styling) {
        var cacheKey = hentFooterPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
    }

    private static String hentCss(DokumentCssStyling styling) {
        var cacheKey = hentCssPathFor(styling).toString();
        return HTML_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));

    }
}
