package no.nav.foreldrepenger.dokgen.test.support;

import java.nio.file.Path;

class FileStructureUtil {

    private static final Path CONTENT_ROOT = Path.of("./content/");
    private static final Path EXPECTED_ROOT = Path.of("src/test/resources/expected/");

    static Path getTemplatePath(Brevmal brevmal) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/template.hbs", brevmal.getNavn()));
    }
    static Path getTemplatePath(Brevmal brevmal, Språk språk) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/template_%s.hbs", brevmal.getNavn(), språk.getKode()));
    }

    static Path getTemplatePath(Brevmal brevmal, String undermal, Språk språk) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/%s_%s.hbs", brevmal.getNavn(), undermal, språk.getKode()));
    }

    static Path getTestDataPath(Brevmal brevmal, String testDataName) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/testdata/%s.json", brevmal.getNavn(), testDataName));
    }

    static Path getTestDataPath(Brevmal brevmal, String undermal, String testDataName) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/testdata/%s/%s.json", brevmal.getNavn(), undermal, testDataName));
    }

    static Path getTemplateRootPath() {
        return CONTENT_ROOT.resolve("templates");
    }

    static Path getExpectedPath(Brevmal brevmal, String expectedFileName) {
        var brevmalNanv = brevmal.getNavn();
        return EXPECTED_ROOT.resolve(String.format("%s/%s", brevmalNanv, expectedFileName));
    }

    static Path getExpectedPath(Brevmal brevmal, String undermal, String expectedFileName) {
        var brevmalNavn = brevmal.getNavn();
        return EXPECTED_ROOT.resolve(String.format("%s/%s/%s", brevmalNavn, undermal, expectedFileName));
    }
}
