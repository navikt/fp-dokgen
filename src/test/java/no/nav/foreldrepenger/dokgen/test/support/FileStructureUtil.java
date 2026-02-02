package no.nav.foreldrepenger.dokgen.test.support;

import java.nio.file.Path;

class FileStructureUtil {

    private static final Path CONTENT_ROOT = Path.of("/content/");
    private static final Path TESTDATA_ROOT = Path.of("/testdata/");
    private static final Path EXPECTED_ROOT = Path.of("/expected/");

    static Path getTemplatePath(Brevmal brevmal) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/template.hbs", brevmal.getNavn()));
    }

    static Path getTemplatePath(Brevmal brevmal, String undermal, Språk språk) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/%s_%s.hbs", brevmal.getNavn(), undermal, språk.getKode()));
    }

    static Path getTestDataPath(Brevmal brevmal, String testDataName) {
        return TESTDATA_ROOT.resolve(String.format("%s/%s.json", brevmal.getNavn(), testDataName));
    }

    static Path getTestDataPath(Brevmal brevmal, String undermal, String testDataName) {
        return TESTDATA_ROOT.resolve(String.format("%s/%s/%s.json", brevmal.getNavn(), undermal, testDataName));
    }

    static Path getExpectedPath(Brevmal brevmal, String expectedFileName) {
        var brevmalNavn = brevmal.getNavn();
        return EXPECTED_ROOT.resolve(String.format("%s/%s", brevmalNavn, expectedFileName));
    }

    static Path getExpectedPath(Brevmal brevmal, String undermal, String expectedFileName) {
        var brevmalNavn = brevmal.getNavn();
        return EXPECTED_ROOT.resolve(String.format("%s/%s/%s", brevmalNavn, undermal, expectedFileName));
    }
}
