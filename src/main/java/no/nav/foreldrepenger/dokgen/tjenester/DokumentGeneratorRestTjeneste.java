package no.nav.foreldrepenger.dokgen.tjenester;

import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;

import jakarta.validation.Valid;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.CacheControl;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokGeneratorTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokSpråk;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/dokument")
@Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/{navn}/create-pdf")
    @Operation(
        summary = "Lager en PDF av flettefeltene og malen med angitt variation.",
        description = "PDF er av versjonen PDF/A"
    )
    public Response createPdf(
        @BeanParam @Valid GenererDokumentParams params,
        @NotNull @RequestBody(description = "JSON-data som skal flettes inn i malen. Må validere mot malens skjema.") String data) {

        var pdf = dokGenTjeneste.createPdf(params.navn(), data, mapSpråk(params.språk()), mapFormat(params.styling()));

        var cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        cacheControl.setMustRevalidate(true);

        return Response.ok(pdf, "application/pdf")
            .header("Content-Disposition", "inline; filename=\"" + params.navn() + ".pdf\"")
            .cacheControl(cacheControl)
            .build();
    }

    public static class GenererDokumentParams {
        @NotNull
        @PathParam("navn")
        private String navn;

        @QueryParam("språk")
        @DefaultValue("BOKMÅL")
        private Språk språk;

        @QueryParam("styling")
        @DefaultValue("PDF")
        private Styling styling;

        public String navn() {
            return navn;
        }

        public Språk språk() {
            return språk;
        }

        public Styling styling() {
            return styling;
        }

        public enum Språk {
            BOKMÅL,
            NYNORSK,
            ENGELSK,
        }

        public enum Styling {
            PDF, HTML, PDFINNTEKTSMELDING
        }
    }

    private DokStyling mapFormat(GenererDokumentParams.Styling styling) {
        return switch (styling) {
            case PDF -> DokStyling.PDF;
            case HTML -> DokStyling.HTML;
            case PDFINNTEKTSMELDING -> DokStyling.PDFINNTEKTSMELDING;
        };
    }

    private DokSpråk mapSpråk(GenererDokumentParams.Språk språk) {
        return switch (språk) {
            case BOKMÅL -> DokSpråk.BOKMÅL;
            case NYNORSK -> DokSpråk.NYNORSK;
            case ENGELSK -> DokSpråk.ENGELSK;
        };
    }

}
