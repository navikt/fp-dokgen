package no.nav.foreldrepenger.dokgen.test.support;

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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemplateTestService {

    private final Handlebars handlebars;

    public TemplateTestService() {
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
        handlebars.registerHelper("divide", new DivideHelper());
        handlebars.registerHelper("formaterKroner", new FormaterKronerHelper());
        handlebars.registerHelpers(StringHelpers.class);
    }

    public String compileTemplateWithTestData(String templateName, String templatePath, String språk, String testDataFilename) throws Exception {
        String templateContent = readFile(FileStructureUtil.getTemplatePath(templateName + templatePath, språk));

        String mergeFieldsJsonString = readFile(FileStructureUtil.getTestDataPath(templateName, testDataFilename));
        JsonNode mergeFields = getJsonFromString(mergeFieldsJsonString);

        Template template = handlebars.compileInline(templateContent);
        return template.apply(with(mergeFields));
    }

    public String getExpectedResult(String templateName, String expectedFileName) throws Exception {
        Path expectedPath = FileStructureUtil.getExpectedPath(templateName, expectedFileName);
        return readFile(expectedPath);
    }

    private String readFile(Path file) throws Exception {
        return Files.readString(file);
    }

    private JsonNode getJsonFromString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

    private Context with(JsonNode model) {
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
