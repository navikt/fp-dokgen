package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator;

import jakarta.validation.constraints.NotNull;

class DokumentMal {

    @NotNull
    private final String navn;
    @NotNull
    private final String innhold;
    @NotNull
    private final DokumentSpråk språk;
    @NotNull
    private final DokumentCssStyling styling;

    private DokumentMal(Builder builder) {
        this.navn = builder.navn;
        this.innhold = builder.innhold;
        this.språk = builder.språk;
        this.styling = builder.styling;
    }

    public String getNavn() {
        return navn;
    }

    public String getInnhold() {
        return innhold;
    }

    public DokumentSpråk getSpråk() {
        return språk;
    }

    public DokumentCssStyling getStyling() {
        return styling;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String navn;
        private String innhold;
        private DokumentSpråk språk = DokumentSpråk.BOKMÅL;
        private DokumentCssStyling styling = DokumentCssStyling.FOR_PDF;

        public Builder medNavn(String navn) {
            this.navn = navn;
            return this;
        }

        public Builder medInnhold(String innhold) {
            this.innhold = innhold;
            return this;
        }

        public Builder medSpråk(DokumentSpråk språk) {
            this.språk = språk;
            return this;
        }

        public Builder medStyling(DokumentCssStyling styling) {
            this.styling = styling;
            return this;
        }

        public DokumentMal build() {
            return new DokumentMal(this);
        }
    }

    @Override
    public String toString() {
        return "DokumentMal{" + "navn='" + navn + '\'' + ", språk=" + språk + ", styling=" + styling + '}';
    }
}
