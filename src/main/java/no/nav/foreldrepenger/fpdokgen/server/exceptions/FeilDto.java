package no.nav.foreldrepenger.fpdokgen.server.exceptions;

import static no.nav.foreldrepenger.fpdokgen.server.exceptions.FeilDto.FeilType.GENERELL_FEIL;

public record FeilDto(FeilType type, String feilmelding) {

    public FeilDto(String feilmelding) {
        this(GENERELL_FEIL, feilmelding);
    }

    public enum FeilType {
        GENERELL_FEIL,
        MANGLER_TILGANG_FEIL,
        VALIDERINGSFEIL,
        JSON_PARSE_FEIL,
    }
}
