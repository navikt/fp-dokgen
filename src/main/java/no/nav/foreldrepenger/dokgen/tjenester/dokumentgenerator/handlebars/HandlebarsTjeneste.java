package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.handlebars;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.jackson.JsonNodeValueResolver;

import jakarta.enterprise.context.Dependent;

@Dependent
public class HandlebarsTjeneste {

    private final Handlebars handlebars;

    public HandlebarsTjeneste() {
        var loader = new ClassPathTemplateLoader();
        loader.setCharset(StandardCharsets.UTF_8);
        loader.setPrefix("/content/templates/");
        loader.setSuffix(".hbs");

        handlebars = new Handlebars(loader);
        handlebars.setCharset(StandardCharsets.UTF_8);
        handlebars.setInfiniteLoops(false);

        // Conditional helpers
        handlebars.registerHelper("eq", ConditionalHelpers.eq);
        handlebars.registerHelper("neq", ConditionalHelpers.neq);
        handlebars.registerHelper("gt", ConditionalHelpers.gt);
        handlebars.registerHelper("gte", ConditionalHelpers.gte);
        handlebars.registerHelper("lt", ConditionalHelpers.lt);
        handlebars.registerHelper("lte", ConditionalHelpers.lte);
        handlebars.registerHelper("and", ConditionalHelpers.and);
        handlebars.registerHelper("or", ConditionalHelpers.or);
        handlebars.registerHelper("not", ConditionalHelpers.not);

        // Custom helpers
        handlebars.registerHelper("arbeidsforhold-fra-orgnummer", new HandlebarsCustomHelpers.ArbeidsforholdLookupHelper());
        handlebars.registerHelper("size", new HandlebarsCustomHelpers.SizeHelper());
        handlebars.registerHelper("switch", new HandlebarsCustomHelpers.SwitchHelper());
        handlebars.registerHelper("case", new HandlebarsCustomHelpers.CaseHelper());
        handlebars.registerHelper("table", new HandlebarsCustomHelpers.TableHelper());
        handlebars.registerHelper("add", new HandlebarsCustomHelpers.AdditionHelper());
        handlebars.registerHelper("norwegian-date", new HandlebarsCustomHelpers.NorwegianDateHelper());
        handlebars.registerHelper("norwegian-datetime", new HandlebarsCustomHelpers.NorwegianDateTimeHelper());
        handlebars.registerHelper("divide", new HandlebarsCustomHelpers.DivideHelper());
        handlebars.registerHelper("thousand-seperator", new HandlebarsCustomHelpers.ThousandSeperatorHelper());
        handlebars.registerHelper("thousand-seperator-double", new HandlebarsCustomHelpers.ThousandSeperatorHelperDouble());
        handlebars.registerHelper("trim-decimal", new HandlebarsCustomHelpers.TrimDecimalHelper());
        handlebars.registerHelper("array", new HandlebarsCustomHelpers.ArrayHelper());
        handlebars.registerHelper("in-array", new HandlebarsCustomHelpers.InArrayHelper());
        handlebars.registerHelper("format-text", new HandlebarsCustomHelpers.FormatText());
        handlebars.registerHelper("land-norsk", new HandlebarsCustomHelpers.CountryCodeHelper());
        handlebars.registerHelper("antall-virkedager", new HandlebarsCustomHelpers.AntallVirkedagerMellomToDatoer());

        // String helpers
        handlebars.registerHelpers(StringHelpers.class);
    }

    public Handlebars getHandlebars() {
        return handlebars;
    }

    public String genererDokumentInnhold(String mal, Map<String, Object> data) {
        try {
            return handlebars.compileInline(mal).apply(contextOf(data));
        } catch (IOException e) {
            throw new IllegalStateException("Kunne ikke kompilere mal", e);
        }
    }

    public Context contextOf(Map<String, Object> model) {
        return Context.newBuilder(model)
            .resolver(JsonNodeValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE, MapValueResolver.INSTANCE, MethodValueResolver.INSTANCE)
            .build();
    }
}
