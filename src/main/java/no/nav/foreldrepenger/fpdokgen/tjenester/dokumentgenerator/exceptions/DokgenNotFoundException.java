package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.exceptions;

public class DokgenNotFoundException extends RuntimeException {
    public DokgenNotFoundException(String melding) {
        super(melding);
    }
}
