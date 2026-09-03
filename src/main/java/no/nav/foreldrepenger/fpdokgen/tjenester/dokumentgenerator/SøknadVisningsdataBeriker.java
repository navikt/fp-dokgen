package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SøknadVisningsdataBeriker {

    private static final String SØKNAD_FORELDREPENGER = "søknad-foreldrepenger";
    private static final String SØKNAD_SVANGERSKAPSPENGER = "søknad-svangerskapspenger";
    private static final String SELVSTENDIG_NÆRING = "selvstendigNæring";
    private static final String FRILANSOPPDRAG = "frilansoppdrag";

    private SøknadVisningsdataBeriker() {
    }

    public static Map<String, Object> berik(String malNavn, Map<String, Object> data) {
        if (!SØKNAD_FORELDREPENGER.equals(malNavn) && !SØKNAD_SVANGERSKAPSPENGER.equals(malNavn)) {
            return data;
        }

        var søkerinfo = map(data.get("søkerinfo"));
        var selvstendigNæringForelagt = harVerdi(søkerinfo, SELVSTENDIG_NÆRING);
        var frilansoppdragForelagt = harVerdi(søkerinfo, FRILANSOPPDRAG);

        var visningsdata = new HashMap<String, Object>();
        visningsdata.put("nyAktivitetsflyt", selvstendigNæringForelagt || frilansoppdragForelagt);
        visningsdata.put("selvstendigNæringForelagt", selvstendigNæringForelagt);
        visningsdata.put("frilansoppdragForelagt", frilansoppdragForelagt);
        visningsdata.put("grupperteFrilansoppdrag", grupperFrilansoppdrag(liste(søkerinfo.get(FRILANSOPPDRAG))));
        visningsdata.put("egenNæring",
            berikEgenNæring(data.get("egenNæring"), liste(søkerinfo.get(SELVSTENDIG_NÆRING))).orElse(null));

        var beriketData = new HashMap<>(data);
        beriketData.put("_dokgen", visningsdata);
        return beriketData;
    }

    private static boolean harVerdi(Map<String, Object> data, String felt) {
        return data.containsKey(felt) && data.get(felt) != null;
    }

    private static Optional<Map<String, Object>> berikEgenNæring(Object egenNæring, List<?> registrerteNæringer) {
        if (!(egenNæring instanceof Map<?, ?> næring) || næring.isEmpty()) {
            return Optional.empty();
        }

        var organisasjonsnumre = registrerteNæringer.stream()
            .map(SøknadVisningsdataBeriker::map)
            .map(registrertNæring -> tekst(registrertNæring.get("organisasjonsnummer")))
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

        var beriketNæring = new HashMap<String, Object>();
        næring.forEach((nøkkel, verdi) -> beriketNæring.put(nøkkel.toString(), verdi));
        beriketNæring.put("forelagt", organisasjonsnumre.contains(tekst(næring.get("organisasjonsnummer"))));
        return Optional.of(beriketNæring);
    }

    private static List<Map<String, Object>> grupperFrilansoppdrag(List<?> oppdrag) {
        var grupper = new LinkedHashMap<String, Frilansgruppe>();
        for (var periode : oppdrag) {
            var oppdragsdata = map(periode);
            var navn = tekst(oppdragsdata.get("navn"));
            grupper.computeIfAbsent(navn == null ? "" : navn, Frilansgruppe::new).leggTil(oppdragsdata);
        }
        return grupper.values().stream().map(Frilansgruppe::tilModell).toList();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> map(Object verdi) {
        return verdi instanceof Map<?, ?> map ? (Map<String, Object>) map : Map.of();
    }

    private static List<?> liste(Object verdi) {
        return verdi instanceof List<?> liste ? liste : List.of();
    }

    private static String tekst(Object verdi) {
        return verdi == null ? null : verdi.toString();
    }

    private static final class Frilansgruppe {
        private final String navn;
        private int antallOppdrag;
        private LocalDate tidligsteFom;
        private LocalDate senesteTom;
        private boolean pågående;

        private Frilansgruppe(String navn) {
            this.navn = navn;
        }

        private static LocalDate dato(Object verdi) {
            var tekst = tekst(verdi);
            return tekst == null || tekst.isBlank() ? null : LocalDate.parse(tekst);
        }

        private void leggTil(Map<String, Object> oppdrag) {
            antallOppdrag++;
            var fom = dato(oppdrag.get("fom"));
            if (fom != null && (tidligsteFom == null || fom.isBefore(tidligsteFom))) {
                tidligsteFom = fom;
            }

            var tom = dato(oppdrag.get("tom"));
            if (tom == null) {
                pågående = true;
            } else if (senesteTom == null || tom.isAfter(senesteTom)) {
                senesteTom = tom;
            }
        }

        private Map<String, Object> tilModell() {
            var modell = new HashMap<String, Object>();
            modell.put("navn", navn);
            modell.put("antallOppdrag", antallOppdrag);
            modell.put("fom", tidligsteFom == null ? null : tidligsteFom.toString());
            modell.put("tom", pågående || senesteTom == null ? null : senesteTom.toString());
            return modell;
        }
    }
}
