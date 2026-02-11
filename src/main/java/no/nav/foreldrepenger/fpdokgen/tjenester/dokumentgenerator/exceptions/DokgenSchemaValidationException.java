package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.exceptions;

public class DokgenSchemaValidationException extends RuntimeException {
    public DokgenSchemaValidationException(String melding) {
        super(melding);
    }
}

