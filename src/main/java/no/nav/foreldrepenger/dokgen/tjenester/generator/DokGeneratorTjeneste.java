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

    public String createHtml(String malNavn, String dataFelter, DokSpråk språk, DokStyling styling) {
        LOG.info("Genererer HTML for mal={} språk={} styling={}", malNavn, språk, styling);
        return createHtml(hentDokMal(malNavn, språk, styling), dataFelter);
    }

    private String createHtml(DokMal dokMal, String dataFelter) {
        var jsonDataMap = validerDataMotSchema(dokMal.getNavn(), dataFelter);
        return konverterTilHtml(dokMal, jsonDataMap);
    }

    public byte[] createPdf(String malNavn, String dataFelter, DokSpråk språk, DokStyling styling) {
        LOG.info("Genererer PDF for mal={} språk={} styling={}", malNavn, språk, styling);
        return createPdf(hentDokMal(malNavn, språk, styling), dataFelter);
    }

    private DokMal hentDokMal(String malNavn, DokSpråk språk, DokStyling styling) {
        var malInnhold = switch (språk) {
            case BOKMÅL, NYNORSK, ENGELSK -> hentPathForMal(malNavn, språk.toString());
            case null -> hentPathForMal(malNavn);
        };
        return hentDokMal(malNavn, malInnhold, språk, styling);
    }

    private DokMal hentDokMal(String malNavn, Path malPath, DokSpråk språk, DokStyling styling) {
        var cacheKey = cacheKey(malPath);
        var malInnhold = MAL_CACHE.computeIfAbsent(cacheKey, key -> lesRessursSomString(Path.of(key)));
        return DokMal.builder().medNavn(malNavn).medInnhold(malInnhold).medSpråk(språk).medStyling(styling).build();
    }

    private String cacheKey(Path malPath) {
        return malPath.toString();
    }

    private byte[] createPdf(DokMal dokMal, String dataFelter) {
        var jsonDataMap = validerDataMotSchema(dokMal.getNavn(), dataFelter);
        return konverterTilPdf(dokMal, jsonDataMap);
    }

    private Map<String, Object> validerDataMotSchema(String malNavn, String dataFelter) {
        LOG.info("Validerer data mot schema for mal={}", malNavn);
        var jsonDataMap = getJsonMapFromString(dataFelter);
        jsonSchemaTjeneste.validerDataMotSchema(jsonDataMap, hentSchemaPathForMal(malNavn));
        return jsonDataMap;
    }

    private byte[] konverterTilPdf(DokMal dokMal, Map<String, Object> dataMap) {
        var htmlMedStyling = konverterTilHtml(dokMal, dataMap);
        return pdfGeneratorTjeneste.genererPdf(htmlMedStyling);
    }

    private String konverterTilHtml(DokMal dokMal, Map<String, Object> dataMap) {
        var markdownMedData = kombinerMalMedData(dokMal, dataMap);
        var innholdHtml = MarkdownUtil.konverterTilHtml(markdownMedData);
        return HtmlUtil.utvidMedHtmlMetadataHeaderFooter(innholdHtml, dokMal.getStyling());
    }

    private String kombinerMalMedData(DokMal dokMal, Map<String, Object> data) {
        return handlebarsTjeneste.genererDokumentInnhold(dokMal.getInnhold(), data);
    }

}
