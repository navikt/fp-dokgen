package no.nav.foreldrepenger.dokgen.tjenester.generator;

public enum DokSpråk {
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
