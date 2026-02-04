package no.nav.foreldrepenger.dokgen.tjenester.exceptions;

import java.util.Map;

public class DokgenSchemaValidationException extends RuntimeException {

    private Map<String, String> validationErrors;

    public DokgenSchemaValidationException(Map<String, String> validationErrors, String melding, Throwable cause) {
        super(melding, cause);
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}

