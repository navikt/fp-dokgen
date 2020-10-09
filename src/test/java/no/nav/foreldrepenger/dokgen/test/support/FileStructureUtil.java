package no.nav.foreldrepenger.dokgen.test.support;

import java.nio.file.Path;

public class FileStructureUtil {

    private static final Path CONTENT_ROOT = Path.of("./content/");
    private static final Path EXPECTED_ROOT = Path.of("src/test/resources/expected/");

    public static Path getTemplatePath(String templateName, String språk) {
        return CONTENT_ROOT.resolve("templates/" + templateName + "/template_" + språk + ".hbs");
    }

    public static Path getTestDataPath(String templateName, String testDataName) {
        return CONTENT_ROOT.resolve("templates/" + templateName + "/testdata/" + testDataName + ".json");
    }

    public static Path getTemplateRootPath() {
        return CONTENT_ROOT.resolve("templates");
    }

    public static Path getExpectedPath(String templateName, String expectedFileName) {
        return EXPECTED_ROOT.resolve(templateName + "/" + expectedFileName);
    }
}
