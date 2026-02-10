package no.nav.foreldrepenger.dokgen.tjenester;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;

public class DokumentGeneratorRequest {
    @NotNull
    @FormParam("malNavn")
    private String malNavn;

    @FormParam("språk")
    private Språk språk;

    @NotNull
    @FormParam("cssStyling")
    @DefaultValue("PDF")
    private DokumentGeneratorRequest.CssStyling cssStyling;

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
}
