package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.exceptions;

public class DokgenNotFoundException extends RuntimeException {
    public DokgenNotFoundException(String melding) {
        super(melding);
    }
}
