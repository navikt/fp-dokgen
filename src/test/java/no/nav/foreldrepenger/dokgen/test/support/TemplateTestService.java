package no.nav.foreldrepenger.dokgen.test.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.AdditionHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.CaseHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.DivideHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.FormatKronerHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.InArrayHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.ArrayHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.SwitchHelper;
import no.nav.foreldrepenger.dokgen.test.handlebarshelpers.TrimDecimalHelper;

public class TemplateTestService {

    private static final Handlebars handlebars;

    static {
        handlebars = Files.exists(FileStructureUtil.getTemplateRootPath())
            ? new Handlebars(new FileTemplateLoader(FileStructureUtil.getTemplateRootPath().toFile()))
            : new Handlebars();
        handlebars.registerHelper("eq", ConditionalHelpers.eq);
        handlebars.registerHelper("neq", ConditionalHelpers.neq);
        handlebars.registerHelper("gt", ConditionalHelpers.gt);
        handlebars.registerHelper("gte", ConditionalHelpers.gte);
        handlebars.registerHelper("lt", ConditionalHelpers.lt);
        handlebars.registerHelper("lte", ConditionalHelpers.lte);
        handlebars.registerHelper("and", ConditionalHelpers.and);
        handlebars.registerHelper("or", ConditionalHelpers.or);
        handlebars.registerHelper("not", ConditionalHelpers.not);
        handlebars.registerHelper("switch", new SwitchHelper());
        handlebars.registerHelper("case", new CaseHelper());
        handlebars.registerHelper("add", new AdditionHelper());
        handlebars.registerHelper("array", new ArrayHelper());
        handlebars.registerHelper("in-array", new InArrayHelper());
        handlebars.registerHelper("divide", new DivideHelper());
        handlebars.registerHelper("format-kroner", new FormatKronerHelper());
        handlebars.registerHelper("trim-decimal", new TrimDecimalHelper());
        handlebars.registerHelpers(StringHelpers.class);
    }

    public TemplateTestService() {
    }

    public static String compileContent(Brevmal templateName, String undermal, Språk språk, String testDataFilename) throws Exception {
        String templateContent = readFile(FileStructureUtil.getTemplatePath(templateName, undermal, språk));
        return produserTekst(templateName, testDataFilename, templateContent);
    }

    public static String compileContent(Brevmal templateName, Språk språk, String testDataFilename) throws Exception {
        String templateContent = readFile(FileStructureUtil.getTemplatePath(templateName, språk));
        return produserTekst(templateName, testDataFilename, templateContent);
    }

    private static String produserTekst(final Brevmal templateName, final String testDataFilename, final String templateContent) throws Exception {
        String mergeFieldsJsonString = readFile(FileStructureUtil.getTestDataPath(templateName, testDataFilename));
        JsonNode mergeFields = getJsonFromString(mergeFieldsJsonString);

        Template template = handlebars.compileInline(templateContent);
        return template.apply(with(mergeFields));
    }

    public static String getExpected(Brevmal brevmal, String expectedFileName) throws Exception {
        Path expectedPath = FileStructureUtil.getExpectedPath(brevmal, expectedFileName);
        return readFile(expectedPath);
    }

    public static String getExpected(Brevmal brevmal, String undermal, String expectedFileName) throws Exception {
        Path expectedPath = FileStructureUtil.getExpectedPath(brevmal, undermal, expectedFileName);
        return readFile(expectedPath);
    }

    private static String readFile(Path file) throws Exception {
        return Files.readString(file);
    }

    private static JsonNode getJsonFromString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

    private static Context with(JsonNode model) {
        return Context
                .newBuilder(model)
                .resolver(JsonNodeValueResolver.INSTANCE,
                        JavaBeanValueResolver.INSTANCE,
                        FieldValueResolver.INSTANCE,
                        MapValueResolver.INSTANCE,
                        MethodValueResolver.INSTANCE
                ).build();
    }


}
