package no.nav.foreldrepenger.fpdokgen.tjenester.v1;

import static no.nav.foreldrepenger.fpdokgen.tjenester.v1.DokumentGeneratorTjenesteMapper.mapTilDokCssStyling;
import static no.nav.foreldrepenger.fpdokgen.tjenester.v1.DokumentGeneratorTjenesteMapper.mapTilDokSpråk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.DokumentGeneratorTjeneste;

@RequestScoped
public class DokumentGeneratorRestTjeneste {

    private static final Logger LOG = LoggerFactory.getLogger(DokumentGeneratorRestTjeneste.class);

    private DokumentGeneratorTjeneste dokumentGeneratorTjeneste;

    DokumentGeneratorRestTjeneste() {
        // for CDI
    }

    @Inject
    public DokumentGeneratorRestTjeneste(DokumentGeneratorTjeneste dokumentGeneratorTjeneste) {
        this.dokumentGeneratorTjeneste = dokumentGeneratorTjeneste;
    }

    public byte[] genererPdfDokument(DokumentGeneratorRequest req) {
        LOG.info("Start PDF generering av ´{}´ som i ´{}´ språk og ´{}´ CSS styling.", req.malNavn(), req.språk(), req.cssStyling());
        var pdf = dokumentGeneratorTjeneste.byggPdf(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()),
            mapTilDokCssStyling(req.cssStyling()));
        LOG.info("Ferdig PDF generering for mal ´{}´: {} bytes", req.malNavn(), pdf.length);
        return pdf;
    }

    public String genererHtmlDokument(DokumentGeneratorRequest req) {
        LOG.info("Start HTML generering av ´{}´ som i ´{}´ språk og ´{}´ CSS styling.", req.malNavn(), req.språk(), req.cssStyling());
        var html = dokumentGeneratorTjeneste.byggHtml(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()),
            mapTilDokCssStyling(req.cssStyling()));
        LOG.info("Ferdig HTML generering for mal ´{}´: {} bytes", req.malNavn(), html.length());
        return html;
    }
}
