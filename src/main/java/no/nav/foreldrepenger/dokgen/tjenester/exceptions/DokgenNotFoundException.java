package no.nav.foreldrepenger.dokgen.tjenester.exceptions;

public class DokgenNotFoundException extends RuntimeException {
    public DokgenNotFoundException(String melding) {
        super(melding);
    }
}
