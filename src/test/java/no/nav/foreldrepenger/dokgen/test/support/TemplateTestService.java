package no.nav.foreldrepenger.dokgen.test.support;

import java.nio.file.Path;
import java.util.Map;

import no.nav.foreldrepenger.dokgen.tjenester.handlebars.HandlebarsTjeneste;
import no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil;
import no.nav.foreldrepenger.dokgen.tjenester.utils.JacksonUtil;

import static no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil.hentPathForMal;

public final class TemplateTestService {


    private TemplateTestService() {
    }

    public static Map<String, Object> getTestDataJson(Brevmal brevmal, String undermal, String testDataFilename) {
        var mergeFieldsJsonString = readContent(FileStructureUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return getJsonFromString(mergeFieldsJsonString);
    }

    public static String compileContent(Brevmal brevmal, String undermal, Språk språk, String testDataFilename) {
        var templateContent = readContent(FileStructureUtil.getTemplatePath(brevmal, undermal, språk));
        var mergeFieldsJsonString = readContent(FileStructureUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(Brevmal brevmal, Språk språk, String testDataFilename) {
        String templateContent;
        if (språk == null) {
            templateContent = readContent(FileStructureUtil.getTemplatePath(brevmal));
        } else {
            templateContent = readContent(hentPathForMal(brevmal.getNavn(), språk.getKode()));
        }
        var mergeFieldsJsonString = readContent(FileStructureUtil.getTestDataPath(brevmal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(Brevmal brevmal, Språk språk, Map<String, Object> testData) {
        var templateContent = readContent(hentPathForMal(brevmal.getNavn(), språk.getKode()));
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

    public static String getExpected(Brevmal brevmal, String expectedFileName) {
        var expectedPath = FileStructureUtil.getExpectedPath(brevmal, expectedFileName);
        return readContent(expectedPath);
    }

    public static String getExpected(Brevmal brevmal, String undermal, String expectedFileName) {
        var expectedPath = FileStructureUtil.getExpectedPath(brevmal, undermal, expectedFileName);
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
