package no.nav.foreldrepenger.dokgen.tjenester.generator;

import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentPathForMal;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentSchemaPathForMal;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.lesRessursSomString;
import static no.nav.foreldrepenger.dokgen.tjenester.utils.JacksonUtil.getJsonMapFromString;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import no.nav.foreldrepenger.dokgen.tjenester.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.jsonschema.JsonSchemaTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.pdf.PdfGeneratorTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.utils.HtmlUtil;
import no.nav.foreldrepenger.dokgen.tjenester.utils.MarkdownUtil;

@ApplicationScoped
public class DokGeneratorTjeneste {

    private static final ConcurrentHashMap<String, String> MAL_CACHE = new ConcurrentHashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(DokGeneratorTjeneste.class);

    private JsonSchemaTjeneste jsonSchemaTjeneste;
    private HandlebarsTjeneste handlebarsTjeneste;
    private PdfGeneratorTjeneste pdfGeneratorTjeneste;

    DokGeneratorTjeneste() {
        // for CDI
    }

    @Inject
    public DokGeneratorTjeneste(JsonSchemaTjeneste jsonSchemaTjeneste,
                                HandlebarsTjeneste handlebarsTjeneste,
                                PdfGeneratorTjeneste pdfGeneratorTjeneste) {
        this.jsonSchemaTjeneste = jsonSchemaTjeneste;
        this.handlebarsTjeneste = handlebarsTjeneste;
        this.pdfGeneratorTjeneste = pdfGeneratorTjeneste;
    }

    public byte[] createPdf(String malNavn, String dataFelter, DokSpråk språk, DokStyling styling) {
        return createPdf(hentDokMal(malNavn, språk, styling), dataFelter);
    }

    private byte[] createPdf(DokMal dokMal, String dataFelter) {
        var jsonDataMap = getJsonMapFromString(dataFelter);
        jsonSchemaTjeneste.validerDataMotSchema(jsonDataMap, hentSchemaPathForMal(dokMal.getNavn()));
        return konverterTilPdf(dokMal, jsonDataMap);
    }

    private byte[] konverterTilPdf(DokMal dokMal, Map<String, Object> dataMap) {
        var markdownMedData = kombinerMalMedData(dokMal, dataMap);
        var htmlMesStyling = konverterTilHtml(markdownMedData, DokStyling.FOR_PDF);
        return pdfGeneratorTjeneste.genererPdf(htmlMesStyling);
    }

    String kombinerMalMedData(DokMal dokMal, Map<String, Object> data) {
        return handlebarsTjeneste.genererDokumentInnhold(dokMal.getInnhold(), data);
    }

    private DokMal hentDokMal(String malNavn, DokSpråk språk, DokStyling styling) {
        return hentDokMal(malNavn, hentPathForMal(malNavn, språk.toString()), språk, styling);
    }

    private DokMal hentDokMal(String malNavn, Path malPath, DokSpråk språk, DokStyling styling) {
        var cacheKey = cacheKey(malPath);
        var malInnhold = MAL_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
        return DokMal.builder().medNavn(malNavn).medInnhold(malInnhold).medSpråk(språk).medStyling(styling).build();
    }

    private String cacheKey(Path malPath) {
        return malPath.toString();
    }

    private String konverterTilHtml(String markdownMedData, DokStyling format) {
        var innholdHtml = MarkdownUtil.konverterTilHtml(markdownMedData);
        return HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innholdHtml, format);
    }

}
