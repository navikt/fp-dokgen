package no.nav.foreldrepenger.dokgen.test.support;

public enum Språk {
    ENGELSK("en"),
    NYNORSK("nn"),
    BOKMÅL("nb");

    private String kode;

    Språk(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }
}
