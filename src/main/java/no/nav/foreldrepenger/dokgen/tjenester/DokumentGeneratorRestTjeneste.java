package no.nav.foreldrepenger.dokgen.tjenester;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokGeneratorTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokSpråk;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;
import no.nav.vedtak.util.InputValideringRegex;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/{navn}/generer")
    @Operation(
        summary = "Lager en PDF av flettefeltene og malen med angitt variation.",
        description = "PDF er av versjonen PDF/A"
    )
    public Response genererDokument(
        @BeanParam @Valid GenererDokumentParams params,
        @NotNull @Valid @Pattern(regexp = InputValideringRegex.FRITEKST) @RequestBody(description = "JSON-data som skal flettes inn i malen. Må validere mot malens skjema.") String data) {
        Objects.requireNonNull(params.navn(), "navn");
        Objects.requireNonNull(params.styling(), "styling");
        Objects.requireNonNull(params.outputType(), "outputType");
        Objects.requireNonNull(data, "data");
        LOG.info("generer {} som {} dokument i {} språk og {} CSS styling.", params.navn(), params.outputType(), params.språk(), params.styling());

        var cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        cacheControl.setMustRevalidate(true);

        if (DokumentType.PDF.equals(params.outputType())) {
            var pdf = dokGenTjeneste.createPdf(params.navn(), data, mapSpråk(params.språk()), mapStyling(params.styling()));
            return Response.ok(pdf, "application/pdf")
                .header("Content-Disposition", "inline; filename=\"" + params.navn() + ".pdf\"")
                .cacheControl(cacheControl)
                .build();
        } else if (DokumentType.HTML.equals(params.outputType())) {
            var html = dokGenTjeneste.createHtml(params.navn(), data, mapSpråk(params.språk()), mapStyling(params.styling()));
            return Response.ok(html, MediaType.TEXT_HTML)
                .header("charset", StandardCharsets.UTF_8.toString())
                .cacheControl(cacheControl)
                .build();
        } else {
            throw new IllegalArgumentException("Ukjent outputType: " + params.outputType());
        }
    }

    public enum Språk {
        BOKMÅL,
        NYNORSK,
        ENGELSK,
    }

    public enum Styling {
        FOR_PDF,
        FOR_HTML,
        FOR_INNTEKTSMELDING_PDF
    }

    public enum DokumentType {
        PDF,
        HTML
    }

    public static class GenererDokumentParams {
        @NotNull
        @PathParam("navn")
        private String navn;

        @QueryParam("språk")
        private Språk språk;

        @QueryParam("styling")
        @DefaultValue("FOR_PDF")
        private Styling styling;

        @QueryParam("outputType")
        @DefaultValue("PDF")
        private DokumentType outputType;

        public String navn() {
            return navn;
        }

        public Språk språk() {
            return språk;
        }

        public Styling styling() {
            return styling;
        }

        public DokumentType outputType() {
            return outputType;
        }
    }

    private DokStyling mapStyling(Styling styling) {
        return switch (styling) {
            case FOR_PDF -> DokStyling.FOR_PDF;
            case FOR_HTML -> DokStyling.FOR_HTML;
            case FOR_INNTEKTSMELDING_PDF -> DokStyling.FOR_INNTEKTSMELDING_PDF;
        };
    }

    private DokSpråk mapSpråk(Språk språk) {
        return switch (språk) {
            case BOKMÅL -> DokSpråk.BOKMÅL;
            case NYNORSK -> DokSpråk.NYNORSK;
            case ENGELSK -> DokSpråk.ENGELSK;
            case null ->  null;
        };
    }

}
