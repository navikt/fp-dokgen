package no.nav.foreldrepenger.dokgen.test.support;

public enum Brevmal {
    //Engangsstønad
    ENGANGSSTØNAD_INNVILGELSE("engangsstonad-innvilgelse"),
    ENGANGSSTØNAD_AVSLAG("engangsstonad-avslag"),

    //Foreldrepenger
    FORELDREPENGER_INNVILGELSE("innvilget-foreldrepenger"),
    FORELDREPENGER_AVSLAG("foreldrepenger-avslag"),
    FORELDREPENGER_OPPHØR("foreldrepenger-opphør"),
    FORELDREPENGER_ANNULLERT("foreldrepenger-annullert"),
    FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER("foreldrepenger-infotilannenforelder"),

    //Støttebrev
    INNHENTE_OPPLYSNINGER("innhente-opplysninger"),
    VARSEL_OM_REVURDERING("varsel-revurdering"),
    INFO_OM_HENLEGGELSE("henleggelse"),
    INNSYN_SVAR("innsyn"),
    IKKE_SØKT("ikke-sokt"),
    INGEN_ENDRING("ingen-endring"),
    FORLENGET_SAKSBEHANDLINGSTID("forlenget-saksbehandlingstid");

    private String navn;

    Brevmal(final String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}
