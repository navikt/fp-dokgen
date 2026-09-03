package content.templates;

import static content.support.TemplateTestUtil.compileContent;
import static content.support.TemplateTestUtil.getExpected;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import content.support.BrevMal;
import content.support.Språk;

class SøknadKvitteringTest {

    @ParameterizedTest(name = "{0} [{1}]")
    @MethodSource("goldenTester")
    void søknad_skal_samsvare_med_golden(GoldenTest test, Språk språk) {
        var content = compileContent(test.brevmal(), språk, test.testdata());
        var expected = getExpected(test.brevmal(), test.forventetFilprefiks() + språk.getKode() + ".txt");
        assertThat(content).isEqualToIgnoringNewLines(expected);
    }

    private static Stream<Arguments> goldenTester() {
        return Stream.of(
            new GoldenTest("SVP utenlandsopphold og avtalt ferie", BrevMal.SVANGESKAPSPENGER_SØKNAD,
                "svp-utenlandsopphold", "svp-utenlandsopphold-ferie-"),
            new GoldenTest("SVP med frilans, næring og arbeid i utlandet", BrevMal.SVANGESKAPSPENGER_SØKNAD,
                "svp-frilans-næring-arbeid-i-utlandet", "svp-frilans-næring-arbeid-i-utlandet-"),
            new GoldenTest("FP med frilans og næring i legacyflyt", BrevMal.FORELDREPENGER_SØKNAD,
                "mor-termin-frilans-næring-legacy", "foreldrepenger-frilans-næring-legacy-"),
            new GoldenTest("SVP med næring i legacyflyt", BrevMal.SVANGESKAPSPENGER_SØKNAD,
                "svp-næring-uten-forelagte-aktiviteter", "svp-næring-uten-forelagte-aktiviteter-"),
            new GoldenTest("SVP med næring uten registertreff", BrevMal.SVANGESKAPSPENGER_SØKNAD,
                "svp-næring-uten-treff-i-registrene", "svp-næring-uten-treff-i-registrene-"),
            new GoldenTest("FP med alle andre inntektskilder", BrevMal.FORELDREPENGER_SØKNAD,
                "mor-termin-alle-andre-inntektskilder", "foreldrepenger-alle-andre-inntektskilder-"),
            new GoldenTest("SVP uten andre inntekter i ny flyt", BrevMal.SVANGESKAPSPENGER_SØKNAD,
                "svp-uten-andre-inntekter-ny-flyt", "svp-uten-andre-inntekter-ny-flyt-"),
            new GoldenTest("Søknad om engangsstønad", BrevMal.ENGANGSSTØNAD_SØKNAD,
                "es", "es-"),
            new GoldenTest("FP med frilans, næring og andre inntekter", BrevMal.FORELDREPENGER_SØKNAD,
                "mor-termin-2af-frilans-næring-andre-inntekter", "foreldrepenger-fl-sn-andre-"),
            new GoldenTest("Søknad om foreldrepenger", BrevMal.FORELDREPENGER_SØKNAD,
                "mor-1-AF-fødsel", "foreldrepenger-"),
            new GoldenTest("Endringssøknad om foreldrepenger", BrevMal.FORELDREPNGER_ENDRING_SØKNAD,
                "endring-bfhr", "foreldrepenger-")
        ).flatMap(test -> Arrays.stream(Språk.values()).map(språk -> Arguments.of(test, språk)));
    }

    private record GoldenTest(String navn, BrevMal brevmal, String testdata, String forventetFilprefiks) {
        @Override
        public String toString() {
            return navn;
        }
    }
}
