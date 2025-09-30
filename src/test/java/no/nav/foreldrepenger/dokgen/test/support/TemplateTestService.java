package no.nav.foreldrepenger.dokgen.test.support;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.jackson.JsonNodeValueResolver;

import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.AdditionHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.AntallVirkedagerMellomToDatoer;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.ArbeidsforholdLookupHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.ArrayHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.CaseHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.CountryCodeHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.DivideHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.FormatKronerHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.InArrayHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.NorwegianDateHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.NorwegianDatetimeHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.SizeHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.SwitchHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.ThousandSeperatorHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.ThousandSeperatorHelperDouble;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.TrimDecimalHelper;

public final class TemplateTestService {

    private static final Handlebars HANDLEBARS;

    static {
        HANDLEBARS = Files.exists(FileStructureUtil.getTemplateRootPath()) ? new Handlebars(
            new FileTemplateLoader(FileStructureUtil.getTemplateRootPath().toFile())) : new Handlebars();
        HANDLEBARS.registerHelper("eq", ConditionalHelpers.eq);
        HANDLEBARS.registerHelper("neq", ConditionalHelpers.neq);
        HANDLEBARS.registerHelper("gt", ConditionalHelpers.gt);
        HANDLEBARS.registerHelper("gte", ConditionalHelpers.gte);
        HANDLEBARS.registerHelper("lt", ConditionalHelpers.lt);
        HANDLEBARS.registerHelper("lte", ConditionalHelpers.lte);
        HANDLEBARS.registerHelper("and", ConditionalHelpers.and);
        HANDLEBARS.registerHelper("or", ConditionalHelpers.or);
        HANDLEBARS.registerHelper("not", ConditionalHelpers.not);
        HANDLEBARS.registerHelper("switch", new SwitchHelper());
        HANDLEBARS.registerHelper("case", new CaseHelper());
        HANDLEBARS.registerHelper("add", new AdditionHelper());
        HANDLEBARS.registerHelper("array", new ArrayHelper());
        HANDLEBARS.registerHelper("in-array", new InArrayHelper());
        HANDLEBARS.registerHelper("divide", new DivideHelper());
        HANDLEBARS.registerHelper("format-kroner", new FormatKronerHelper());
        HANDLEBARS.registerHelper("thousand-seperator", new ThousandSeperatorHelper());
        HANDLEBARS.registerHelper("thousand-seperator-double", new ThousandSeperatorHelperDouble());
        HANDLEBARS.registerHelper("trim-decimal", new TrimDecimalHelper());
        HANDLEBARS.registerHelper("size", new SizeHelper());
        HANDLEBARS.registerHelper("norwegian-date", new NorwegianDateHelper());
        HANDLEBARS.registerHelper("norwegian-datetime", new NorwegianDatetimeHelper());
        HANDLEBARS.registerHelper("country", new NorwegianDatetimeHelper());
        HANDLEBARS.registerHelper("land-norsk", new CountryCodeHelper());
        HANDLEBARS.registerHelper("arbeidsforhold-fra-orgnummer", new ArbeidsforholdLookupHelper());
        HANDLEBARS.registerHelper("antall-virkedager", new AntallVirkedagerMellomToDatoer());
        HANDLEBARS.registerHelpers(StringHelpers.class);
    }

    private TemplateTestService() {
    }

    public static ObjectNode getTestDataJson(Brevmal brevmal, String undermal, String testDataFilename) {
        var mergeFieldsJsonString = readFile(FileStructureUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return (ObjectNode) getJsonFromString(mergeFieldsJsonString);
    }

    public static String compileContent(Brevmal brevmal, String undermal, Språk språk, String testDataFilename) {
        var templateContent = readFile(FileStructureUtil.getTemplatePath(brevmal, undermal, språk));
        var mergeFieldsJsonString = readFile(FileStructureUtil.getTestDataPath(brevmal, undermal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(Brevmal brevmal, Språk språk, String testDataFilename) {
        String templateContent;
        if (språk == null) {
            templateContent = readFile(FileStructureUtil.getTemplatePath(brevmal));
        } else {
            templateContent = readFile(FileStructureUtil.getTemplatePath(brevmal, språk));
        }
        var mergeFieldsJsonString = readFile(FileStructureUtil.getTestDataPath(brevmal, testDataFilename));
        return produceContent(mergeFieldsJsonString, templateContent);
    }

    public static String compileContent(Brevmal brevmal, Språk språk, JsonNode testData) {
        var templateContent = readFile(FileStructureUtil.getTemplatePath(brevmal, språk));
        return produceContent(testData, templateContent);
    }

    private static String produceContent(String mergeFieldsJsonString, String templateContent) {
        var mergeFields = getJsonFromString(mergeFieldsJsonString);
        return produceContent(mergeFields, templateContent);
    }

    private static String produceContent(JsonNode mergeFields, String templateContent) {
        try {
            var template = HANDLEBARS.compileInline(templateContent);
            return removeNewLines(template.apply(with(mergeFields)));
        } catch (IOException e) {
            throw new RuntimeException("Feil ved lesing av fil", e);
        }
    }

    public static String getExpected(Brevmal brevmal, String expectedFileName) {
        var expectedPath = FileStructureUtil.getExpectedPath(brevmal, expectedFileName);
        return readFile(expectedPath);
    }

    public static String getExpected(Brevmal brevmal, String undermal, String expectedFileName) {
        var expectedPath = FileStructureUtil.getExpectedPath(brevmal, undermal, expectedFileName);
        return readFile(expectedPath);
    }

    private static String readFile(Path file) {
        try {
            return removeNewLines(Files.readString(file));
        } catch (IOException e) {
            throw new RuntimeException("Feil ved lesing av fil", e);
        }
    }

    private static JsonNode getJsonFromString(String json) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Feil med konvertering til jsonnode", e);
        }
    }

    private static Context with(JsonNode model) {
        var MY_FIELD_VALUE_RESOLVER = new FieldValueResolver() {

            @Override
            protected Set<FieldWrapper> members(Class<?> clazz) {
                var members = super.members(clazz);
                return members.stream().filter(this::isValidField).collect(Collectors.toSet());
            }

            boolean isValidField(FieldWrapper fw) {
                if (fw instanceof AccessibleObject) {
                    return isUseSetAccessible(fw);
                }
                return true;
            }
        };
        return Context.newBuilder(model)
            .resolver(JsonNodeValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE, MY_FIELD_VALUE_RESOLVER, MapValueResolver.INSTANCE,
                MethodValueResolver.INSTANCE)
            .build();
    }

    private static String removeNewLines(String text) {
        return text.replaceAll("(?m)^[ \t]*\r?\n", "");
    }

}
