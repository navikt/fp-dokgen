package content.support;

public enum Språk {
    ENGELSK("en"),
    NYNORSK("nn"),
    BOKMÅL("nb");

    private final String kode;

    Språk(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }
}
