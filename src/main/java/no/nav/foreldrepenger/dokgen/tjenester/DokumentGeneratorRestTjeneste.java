package no.nav.foreldrepenger.dokgen.tjenester;

import static no.nav.foreldrepenger.dokgen.tjenester.DokumentGeneratorTjenesteMapper.mapTilDokCssStyling;
import static no.nav.foreldrepenger.dokgen.tjenester.DokumentGeneratorTjenesteMapper.mapTilDokSpråk;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentGeneratorTjeneste;

@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Path("/dokument")
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

    @POST
    @Path("/generer/pdf")
    @Produces("application/pdf")
    public Response genererPdfDokument(@BeanParam @Valid DokumentGeneratorRequest req) {
        Objects.requireNonNull(req.malNavn(), "malNavn");
        Objects.requireNonNull(req.cssStyling(), "cssStyling");
        Objects.requireNonNull(req.inputData(), "inputData");
        LOG.info("generer {} som PDF dokument i {} språk og {} CSS styling.", req.malNavn(), req.språk(), req.cssStyling());

        var pdf = dokumentGeneratorTjeneste.byggPdf(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()),
            mapTilDokCssStyling(req.cssStyling()));

        return Response.ok(pdf, "application/pdf")
            .header("Content-Disposition", "inline; filename=\"" + req.malNavn() + ".pdf\"")
            .cacheControl(getCacheControl())
            .build();
    }

    @POST
    @Path("/generer/html")
    @Produces(MediaType.TEXT_HTML)
    public Response genererHtmlDokument(@BeanParam @Valid DokumentGeneratorRequest req) {
        Objects.requireNonNull(req.malNavn(), "malNavn");
        Objects.requireNonNull(req.cssStyling(), "cssStyling");
        Objects.requireNonNull(req.inputData(), "inputData");
        LOG.info("generer {} som HTML dokument i {} språk og {} CSS styling.", req.malNavn(), req.språk(), req.cssStyling());

        var html = dokumentGeneratorTjeneste.byggHtml(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()),
            mapTilDokCssStyling(req.cssStyling()));

        return Response.ok(html, MediaType.TEXT_HTML)
            .header("charset", StandardCharsets.UTF_8.toString())
            .cacheControl(getCacheControl())
            .build();
    }

    private static CacheControl getCacheControl() {
        var cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        cacheControl.setMustRevalidate(true);
        return cacheControl;
    }
}
