package no.nav.foreldrepenger.dokgen.tjenester;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import jakarta.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokCssStyling;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokGeneratorTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokSpråk;

@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Path("/dokument")
public class DokumentGeneratorRestTjeneste {

    private static final Logger LOG = LoggerFactory.getLogger(DokumentGeneratorRestTjeneste.class);

    private DokGeneratorTjeneste dokGenTjeneste;

    DokumentGeneratorRestTjeneste() {
        // for CDI
    }

    @Inject
    public DokumentGeneratorRestTjeneste(DokGeneratorTjeneste dokGenTjeneste) {
        this.dokGenTjeneste = dokGenTjeneste;
    }

    @POST
    @Path("/generer/pdf")
    @Produces("application/pdf")
    @Operation(
        tags = "generer-dokument",
        summary = "Genererer PDF/A dokument basert på angitt mal, språk og CSS-styling med JSON-datainnhold.",
        description = "Genererer et dokument ved å flette JSON-data inn i en Handlebars-mal. " +
                      "Støtter output i PDF/A-format (for arkivering)." +
                      "Malen velges via malNavn-parameter, og data må validere mot malens JSON-schema."
    )
    public Response genererPdfDokument(@BeanParam @Valid DokumentGeneratorRestTjeneste.GenererDokumentRequest req) {
        Objects.requireNonNull(req.malNavn(), "malNavn");
        Objects.requireNonNull(req.cssStyling(), "cssStyling");
        Objects.requireNonNull(req.inputData(), "inputData");
        LOG.info("generer {} som PDF dokument i {} språk og {} CSS styling.", req.malNavn(), req.språk(), req.cssStyling());


        var pdf = dokGenTjeneste.createPdf(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()), mapTilDokCssStyling(req.cssStyling()));

        var cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        cacheControl.setMustRevalidate(true);

        return Response.ok(pdf, "application/pdf")
            .header("Content-Disposition", "inline; filename=\"" + req.malNavn() + ".pdf\"")
            .cacheControl(cacheControl)
            .build();
    }

    @POST
    @Path("/generer/html")
    @Produces(MediaType.TEXT_HTML)
    @Operation(
        tags = "generer-dokument",
        summary = "Genererer HTML dokument basert på angitt mal, språk og CSS-styling med JSON-datainnhold.",
        description = "Genererer et dokument ved å flette JSON-data inn i en Handlebars-mal. " +
            "Støtter output i HTML-format (for visning). " +
            "Malen velges via malNavn-parameter, og data må validere mot malens JSON-schema."
    )
    public Response genererHtmlDokument(@BeanParam @Valid DokumentGeneratorRestTjeneste.GenererDokumentRequest req) {
        Objects.requireNonNull(req.malNavn(), "malNavn");
        Objects.requireNonNull(req.cssStyling(), "cssStyling");
        Objects.requireNonNull(req.inputData(), "inputData");
        LOG.info("generer {} som HTML dokument i {} språk og {} CSS styling.", req.malNavn(), req.språk(), req.cssStyling());

        var html = dokGenTjeneste.createHtml(req.malNavn(), req.inputData(), mapTilDokSpråk(req.språk()), mapTilDokCssStyling(req.cssStyling()));

        var cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        cacheControl.setMustRevalidate(true);
        return Response.ok(html, MediaType.TEXT_HTML).header("charset", StandardCharsets.UTF_8.toString()).cacheControl(cacheControl).build();
    }

    public enum Språk {
        BOKMÅL,
        NYNORSK,
        ENGELSK,
    }

    public enum CssStyling {
        PDF,
        HTML,
        INNTEKTSMELDING_PDF
    }

    public static class GenererDokumentRequest {
        @NotNull
        @FormParam("malNavn")
        private String malNavn;

        @FormParam("språk")
        private Språk språk;

        @NotNull
        @FormParam("cssStyling")
        @DefaultValue("PDF")
        private CssStyling cssStyling;

        @NotNull
        @FormParam("inputData")
        private String inputData;

        public String malNavn() {
            return malNavn;
        }

        public Språk språk() {
            return språk;
        }

        public CssStyling cssStyling() {
            return cssStyling;
        }

        public String inputData() {
            return inputData;
        }
    }

    private DokCssStyling mapTilDokCssStyling(CssStyling cssStyling) {
        return switch (cssStyling) {
            case PDF -> DokCssStyling.FOR_PDF;
            case HTML -> DokCssStyling.FOR_HTML;
            case INNTEKTSMELDING_PDF -> DokCssStyling.FOR_INNTEKTSMELDING_PDF;
        };
    }

    private DokSpråk mapTilDokSpråk(Språk språk) {
        return switch (språk) {
            case BOKMÅL -> DokSpråk.BOKMÅL;
            case NYNORSK -> DokSpråk.NYNORSK;
            case ENGELSK -> DokSpråk.ENGELSK;
            case null ->  null;
        };
    }

}
