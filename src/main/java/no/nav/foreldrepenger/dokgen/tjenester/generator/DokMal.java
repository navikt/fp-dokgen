package no.nav.foreldrepenger.dokgen.tjenester.generator;

import jakarta.validation.constraints.NotNull;

public class DokMal {

    @NotNull
    private final String navn;
    @NotNull
    private final String innhold;
    @NotNull
    private final DokSpråk språk;
    @NotNull
    private final DokStyling styling;

    private DokMal(Builder builder) {
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

    public DokSpråk getSpråk() {
        return språk;
    }

    public DokStyling getStyling() {
        return styling;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String navn;
        private String innhold;
        private DokSpråk språk = DokSpråk.BOKMÅL;
        private DokStyling styling = DokStyling.PDF;

        public Builder medNavn(String navn) {
            this.navn = navn;
            return this;
        }

        public Builder medInnhold(String innhold) {
            this.innhold = innhold;
            return this;
        }

        public Builder medSpråk(DokSpråk språk) {
            this.språk = språk;
            return this;
        }

        public Builder medStyling(DokStyling styling) {
            this.styling = styling;
            return this;
        }

        public DokMal build() {
            return new DokMal(this);
        }
    }
}
