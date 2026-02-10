package content.support;

import java.nio.file.Path;
import java.util.Map;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.utils.ContentUtil;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.utils.JacksonUtil;

public final class TemplateTestUtil {

    private TemplateTestUtil() {
        // utility class
    }

    public static Map<String, Object> getTestDataJson(BrevMal brevmal, String undermal, String testDataFilename) {
        var mergeFieldsJsonString = readContent(TestContentUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return getJsonFromString(mergeFieldsJsonString);
    }

    public static String compileContent(BrevMal brevmal, String undermal, Språk språk, String testDataFilename) {
        var templateContent = readContent(TestContentUtil.hentPathForMal(brevmal, undermal, språk));
        var mergeFieldsJsonString = readContent(TestContentUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(BrevMal brevmal, Språk språk, String testDataFilename) {
        String templateContent;
        if (språk == null) {
            templateContent = readContent(ContentUtil.hentPathForMal(brevmal.getNavn()));
        } else {
            templateContent = readContent(ContentUtil.hentPathForMal(brevmal.getNavn(), språk.getKode()));
        }
        var mergeFieldsJsonString = readContent(TestContentUtil.getTestDataPath(brevmal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(BrevMal brevmal, Språk språk, Map<String, Object> testData) {
        var templateContent = readContent(ContentUtil.hentPathForMal(brevmal.getNavn(), språk.getKode()));
        return produceContent(testData, templateContent);
    }

    private static String produceContent(String mergeFieldsJsonString, String templateContent) {
        var mergeFields = getJsonFromString(mergeFieldsJsonString);
        return produceContent(mergeFields, templateContent);
    }

    private static String produceContent(Map<String, Object> mergeFields, String templateContent) {
        var handlebarsTjeneste = new HandlebarsTjeneste();
        return removeNewLines(handlebarsTjeneste.genererDokumentInnhold(templateContent, mergeFields));
    }

    public static String getExpected(BrevMal brevmal, String expectedFileName) {
        var expectedPath = TestContentUtil.getExpectedPath(brevmal, expectedFileName);
        return readContent(expectedPath);
    }

    public static String getExpected(BrevMal brevmal, String undermal, String expectedFileName) {
        var expectedPath = TestContentUtil.getExpectedPath(brevmal, undermal, expectedFileName);
        return readContent(expectedPath);
    }

    private static String readContent(Path file) {
        return removeNewLines(ContentUtil.lesRessursSomString(file));
    }

    private static Map<String, Object> getJsonFromString(String json) {
        return JacksonUtil.getJsonMapFromString(json);
    }


    private static String removeNewLines(String text) {
        return text.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

}
