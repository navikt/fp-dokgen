package no.nav.foreldrepenger.dokgen.test.support;

public enum Brevmal {
    ES_AVSLAG("engangsstonad-avslag"),
    ES_INNVILGET("engangsstonad-innvilgelse"),

    FP_AVSLAG("foreldrepenger-avslag"),
    FP_INFO_TIL_ANNEN_FORELDER("foreldrepenger-infotilannenforelder"),
    FP_INNVILGET("innvilget-foreldrepenger"),

    FORLENGET_SAKSBEHANDLINGSTID("forlenget-saksbehandlingstid"),
    HENLEGELSE("henleggelse"),
    IKKE_SØKT("ikke-sokt"),
    INGEN_ENDRING("ingen-endring"),
    INNHENTE_OPPLYSNINGER("innhente-opplysninger"),
    INNSYN("innsyn"),
    VARSEL_REVURDERING("varsel-revurdering");

    private String navn;

    Brevmal(final String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}
