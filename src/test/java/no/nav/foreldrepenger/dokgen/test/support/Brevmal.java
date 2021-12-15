package no.nav.foreldrepenger.dokgen.test.support;

public enum Brevmal {
    //Engangsstønad
    ENGANGSSTØNAD_INNVILGELSE("engangsstonad-innvilgelse"),
    ENGANGSSTØNAD_AVSLAG("engangsstonad-avslag"),

    //Foreldrepenger
    FORELDREPENGER_INNVILGELSE("foreldrepenger-innvilgelse"),
    FORELDREPENGER_AVSLAG("foreldrepenger-avslag"),
    FORELDREPENGER_OPPHØR("foreldrepenger-opphor"),
    FORELDREPENGER_ANNULLERT("foreldrepenger-annullert"),
    FORELDREPENGER_INFOBREV_TIL_ANNEN_FORELDER("foreldrepenger-infotilannenforelder"),

    //Svangerskapspenger
    SVANGERSKAPSPENGER_INNVILGELSE("svangerskapspenger-innvilgelse"),
    SVANGERSKAPSPENGER_OPPHØR("svangerskapspenger-opphor"),
    SVANGERSKAPSPENGER_AVSLAG("svangerskapspenger-avslag"),

    //Støttebrev
    FRITEKSTBREV("fritekstbrev"),
    INNHENTE_OPPLYSNINGER("innhente-opplysninger"),
    VARSEL_OM_REVURDERING("varsel-revurdering"),
    INFO_OM_HENLEGGELSE("henleggelse"),
    INNSYN_SVAR("innsyn"),
    IKKE_SØKT("ikke-sokt"),
    INGEN_ENDRING("ingen-endring"),
    FORLENGET_SAKSBEHANDLINGSTID("forlenget-saksbehandlingstid"),
    KLAGE_AVVIST("klage-avvist"),
    KLAGE_HJEMSENDT("klage-hjemsendt"),
    KLAGE_OMGJORT("klage-omgjort"),
    KLAGE_OVERSENDT("klage-oversendt"),
    KLAGE_STADFESTET("klage-stadfestet"),
    ETTERLYS_INNTEKTSMELDING("etterlys-inntektsmelding"),
    ANKE_OMGJORT("anke-omgjort"),
    ANKE_OPPHEVET("anke-opphevet"),
    ;

    private String navn;

    Brevmal(final String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }
}
