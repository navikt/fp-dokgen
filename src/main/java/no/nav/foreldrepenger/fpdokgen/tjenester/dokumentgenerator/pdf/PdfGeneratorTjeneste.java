package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.pdf;

import static no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil.lesRessursFra;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.fontbox.ttf.TTFParser;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PDFontSupplier;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.slf4j.Slf4jLogger;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import com.openhtmltopdf.util.XRLog;

import jakarta.enterprise.context.Dependent;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.utils.ContentUtil;

@Dependent
public class PdfGeneratorTjeneste {

    private static final ConcurrentHashMap<String, byte[]> FONT_BYTES_CACHE = new ConcurrentHashMap<>();

    private static final byte[] COLOR_PROFILE;

    static {
        COLOR_PROFILE = lesRessursFra(Path.of("/sRGB2014.icc"));
        XRLog.setLoggingEnabled(false);
        XRLog.setLoggerImpl(new Slf4jLogger());
    }

    PdfGeneratorTjeneste() {
        // for CDI
    }

    public byte[] genererPdf(String htmlInnhold) {
        var outputStream = new ByteArrayOutputStream();
        genererPdf(htmlInnhold, outputStream);
        return outputStream.toByteArray();
    }

    private void genererPdf(String htmlInnhold, ByteArrayOutputStream outputStream) {
        try {
            if (outputStream != null) {
                // Jsoup korrigerer HTML-syntaks før PDF-generering.
                // Eksempel: <br> uten avslutning blir <br/>, som kreves av XML-parseren (TRaX).
                // Brev overstyring sender <br> også, ellers kunne man brukt htmlInnhold direkte.
                var document = Jsoup.parse(htmlInnhold);
                var fontFamily = "Source Sans Pro";
                new PdfRendererBuilder().useFont(fontSupplier("SourceSansPro-Regular.ttf"), fontFamily, 400, BaseRendererBuilder.FontStyle.NORMAL,
                        true)
                    .useFont(fontSupplier("SourceSansPro-Bold.ttf"), fontFamily, 700, BaseRendererBuilder.FontStyle.OBLIQUE, true)
                    .useFont(fontSupplier("SourceSansPro-It.ttf"), fontFamily, 400, BaseRendererBuilder.FontStyle.ITALIC, true)
                    .useColorProfile(COLOR_PROFILE)
                    .useSVGDrawer(new BatikSVGDrawer())
                    .usePdfAConformance(PdfRendererBuilder.PdfAConformance.PDFA_2_U)
                    .usePdfUaAccessibility(true)
                    .withW3cDocument(new W3CDom().fromJsoup(document), "")
                    .toStream(outputStream)
                    .run();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Feil ved generering av PDF", e);
        }
    }

    private PDFontSupplier fontSupplier(String fontName) {
        var fontBytes = FONT_BYTES_CACHE.computeIfAbsent(fontName,
            name -> lesRessursFra(ContentUtil.hentFontDirectoryPath().resolve(name)));
        try {
            var ttf = new TTFParser().parse(new RandomAccessReadBuffer(fontBytes));
            // Slå av GSUB for å unngå problemer med glyph substitution i noen fonter
            ttf.setEnableGsub(false);
            return new PDFontSupplier(PDType0Font.load(new PDDocument(), ttf, true));
        } catch (IOException e) {
            throw new IllegalStateException("Kunne ikke laste font: " + fontName, e);
        }
    }
}
