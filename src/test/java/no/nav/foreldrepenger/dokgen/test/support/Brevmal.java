package no.nav.foreldrepenger.dokgen.test.support;

public enum Brevmal {
    //Engangsstønad
    ENGANGSSTØNAD_AVSLAG("engangsstonad-avslag"),
    ENGANGSSTØNAD_INNVILGELSE("engangsstonad-innvilgelse"),
    //Foreldrepenger
    FORELDREPENGER_INNVILGELSE("innvilget-foreldrepenger"),
    FORELDREPENGER_AVSLAG("foreldrepenger-avslag"),
    FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER("foreldrepenger-infotilannenforelder"),
    //Støtte brev
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
