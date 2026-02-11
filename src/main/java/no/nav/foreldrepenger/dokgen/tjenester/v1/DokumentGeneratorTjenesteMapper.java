package no.nav.foreldrepenger.dokgen.tjenester.v1;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentCssStyling;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentSpråk;

class DokumentGeneratorTjenesteMapper {

    private DokumentGeneratorTjenesteMapper() {
        // Utility class.
    }

    static DokumentCssStyling mapTilDokCssStyling(DokumentGeneratorRequest.CssStyling cssStyling) {
        return switch (cssStyling) {
            case PDF -> DokumentCssStyling.FOR_PDF;
            case HTML -> DokumentCssStyling.FOR_HTML;
            case INNTEKTSMELDING_PDF -> DokumentCssStyling.FOR_INNTEKTSMELDING_PDF;
        };
    }

    static DokumentSpråk mapTilDokSpråk(DokumentGeneratorRequest.Språk språk) {
        return switch (språk) {
            case BOKMÅL -> DokumentSpråk.BOKMÅL;
            case NYNORSK -> DokumentSpråk.NYNORSK;
            case ENGELSK -> DokumentSpråk.ENGELSK;
            case null ->  null;
        };
    }
}
