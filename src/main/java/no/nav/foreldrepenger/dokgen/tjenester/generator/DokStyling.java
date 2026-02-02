package no.nav.foreldrepenger.dokgen.tjenester.generator;

public enum DokStyling {
    FOR_PDF,
    FOR_HTML,
    FOR_INNTEKTSMELDING_PDF;

    @Override
    public String toString() {
        return switch (this) {
            case FOR_PDF -> "pdf";
            case FOR_HTML -> "html";
            case FOR_INNTEKTSMELDING_PDF -> "inntektsmelding_pdf";
        };
    }
}
