package no.nav.foreldrepenger.dokgen.tjenester.generator;

public enum DokStyling {
    PDF,
    HTML,
    PDFINNTEKTSMELDING;

    @Override
    public String toString() {
        return switch (this) {
            case PDF -> "pdf";
            case HTML -> "html";
            case PDFINNTEKTSMELDING -> "pdfinntektsmelding";
        };
    }
}
