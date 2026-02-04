package no.nav.foreldrepenger.dokgen.tjenester.exceptions;

public class DokgenSchemaValidationException extends RuntimeException {
    public DokgenSchemaValidationException(String melding) {
        super(melding);
    }
}

