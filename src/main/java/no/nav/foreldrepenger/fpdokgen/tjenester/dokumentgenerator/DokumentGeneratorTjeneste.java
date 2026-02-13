package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator;

import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.hentPathForMal;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.hentSchemaPathForMal;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.lesRessursSomString;
import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.JacksonUtil.getJsonMapFromString;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.jsonschema.JsonSchemaTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.pdf.PdfGeneratorTjeneste;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.HtmlUtil;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.MarkdownUtil;

@ApplicationScoped
public class DokumentGeneratorTjeneste {

    private static final ConcurrentHashMap<String, String> MAL_CACHE = new ConcurrentHashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(DokumentGeneratorTjeneste.class);

    private JsonSchemaTjeneste jsonSchemaTjeneste;
    private HandlebarsTjeneste handlebarsTjeneste;
    private PdfGeneratorTjeneste pdfGeneratorTjeneste;

    DokumentGeneratorTjeneste() {
        // for CDI
    }

    @Inject
    public DokumentGeneratorTjeneste(JsonSchemaTjeneste jsonSchemaTjeneste,
                                     HandlebarsTjeneste handlebarsTjeneste,
                                     PdfGeneratorTjeneste pdfGeneratorTjeneste) {
        this.jsonSchemaTjeneste = jsonSchemaTjeneste;
        this.handlebarsTjeneste = handlebarsTjeneste;
        this.pdfGeneratorTjeneste = pdfGeneratorTjeneste;
    }

    public String byggHtml(String malNavn, String dataFelter, DokumentSpråk språk, DokumentCssStyling styling) {
        LOG.info("Genererer HTML for mal={} språk={} styling={}", malNavn, språk, styling);
        var dokMal = hentDokumentMal(malNavn, språk, styling);
        var jsonDataMap = validerDataMotSchema(dokMal.getNavn(), dataFelter);
        return konverterTilHtml(dokMal, jsonDataMap);
    }

    public byte[] byggPdf(String malNavn, String dataFelter, DokumentSpråk språk, DokumentCssStyling styling) {
        LOG.info("Genererer PDF for mal={} språk={} styling={}", malNavn, språk, styling);
        return byggPdf(hentDokumentMal(malNavn, språk, styling), dataFelter);
    }

    private DokumentMal hentDokumentMal(String malNavn, DokumentSpråk språk, DokumentCssStyling styling) {
        var malInnhold = switch (språk) {
            case BOKMÅL, NYNORSK, ENGELSK -> hentPathForMal(malNavn, språk.toString());
            case null -> hentPathForMal(malNavn);
        };
        return hentDokumentMal(malNavn, malInnhold, språk, styling);
    }

    private DokumentMal hentDokumentMal(String malNavn, Path malPath, DokumentSpråk språk, DokumentCssStyling styling) {
        var cacheKey = cacheKey(malPath);
        var malInnhold = MAL_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
        return DokumentMal.builder().medNavn(malNavn).medInnhold(malInnhold).medSpråk(språk).medStyling(styling).build();
    }

    private String cacheKey(Path malPath) {
        return malPath.toString();
    }

    private byte[] byggPdf(DokumentMal dokumentMal, String dataFelter) {
        var jsonDataMap = validerDataMotSchema(dokumentMal.getNavn(), dataFelter);
        return konverterTilPdf(dokumentMal, jsonDataMap);
    }

    private Map<String, Object> validerDataMotSchema(String malNavn, String dataFelter) {
        LOG.debug("Validerer data mot schema for mal={}", malNavn);
        var jsonDataMap = getJsonMapFromString(dataFelter);
        jsonSchemaTjeneste.validerDataMotSchema(jsonDataMap, hentSchemaPathForMal(malNavn));
        return jsonDataMap;
    }

    private byte[] konverterTilPdf(DokumentMal dokumentMal, Map<String, Object> dataMap) {
        LOG.debug("Konverterer til HTML: {}", dokumentMal);
        var htmlMedStyling = konverterTilHtml(dokumentMal, dataMap);
        return pdfGeneratorTjeneste.genererPdf(htmlMedStyling);
    }

    private String konverterTilHtml(DokumentMal dokumentMal, Map<String, Object> dataMap) {
        var markdownMedData = kombinerMalMedData(dokumentMal, dataMap);
        var innholdHtml = MarkdownUtil.konverterTilHtml(markdownMedData);
        return HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innholdHtml, dokumentMal.getStyling());
    }

    private String kombinerMalMedData(DokumentMal dokumentMal, Map<String, Object> data) {
        return handlebarsTjeneste.genererDokumentInnhold(dokumentMal.getInnhold(), data);
    }

}
