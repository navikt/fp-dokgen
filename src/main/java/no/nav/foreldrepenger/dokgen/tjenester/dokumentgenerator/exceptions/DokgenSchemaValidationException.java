package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.exceptions;

public class DokgenSchemaValidationException extends RuntimeException {
    public DokgenSchemaValidationException(String melding) {
        super(melding);
    }
}

