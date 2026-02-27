package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator;

public enum DokumentSpråk {
    BOKMÅL,
    NYNORSK,
    ENGELSK;

    @Override
    public String toString() {
        return switch (this) {
            case BOKMÅL -> "nb";
            case NYNORSK -> "nn";
            case ENGELSK -> "en";
        };
    }
}
