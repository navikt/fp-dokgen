package no.nav.foreldrepenger.dokgen.tjenester.v1;

import jakarta.validation.constraints.NotNull;

public class DokumentGeneratorRequest {
    @NotNull
    private String malNavn;

    private Språk språk;

    @NotNull
    private DokumentGeneratorRequest.CssStyling cssStyling;

    @NotNull
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
