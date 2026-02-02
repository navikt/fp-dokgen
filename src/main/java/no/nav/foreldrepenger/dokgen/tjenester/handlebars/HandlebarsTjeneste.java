package no.nav.foreldrepenger.dokgen.tjenester.handlebars;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.jackson.JsonNodeValueResolver;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
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
        handlebars.registerHelper("arbeidsforhold-fra-orgnummer", new CustomHelpers.ArbeidsforholdLookupHelper());
        handlebars.registerHelper("size", new CustomHelpers.SizeHelper());
        handlebars.registerHelper("switch", new CustomHelpers.SwitchHelper());
        handlebars.registerHelper("case", new CustomHelpers.CaseHelper());
        handlebars.registerHelper("table", new CustomHelpers.TableHelper());
        handlebars.registerHelper("add", new CustomHelpers.AdditionHelper());
        handlebars.registerHelper("norwegian-date", new CustomHelpers.NorwegianDateHelper());
        handlebars.registerHelper("norwegian-datetime", new CustomHelpers.NorwegianDateTimeHelper());
        handlebars.registerHelper("divide", new CustomHelpers.DivideHelper());
        handlebars.registerHelper("thousand-seperator", new CustomHelpers.ThousandSeperatorHelper());
        handlebars.registerHelper("thousand-seperator-double", new CustomHelpers.ThousandSeperatorHelperDouble());
        handlebars.registerHelper("trim-decimal", new CustomHelpers.TrimDecimalHelper());
        handlebars.registerHelper("array", new CustomHelpers.ArrayHelper());
        handlebars.registerHelper("in-array", new CustomHelpers.InArrayHelper());
        handlebars.registerHelper("format-text", new CustomHelpers.FormatText());
        handlebars.registerHelper("land-norsk", new CustomHelpers.CountryCodeHelper());
        handlebars.registerHelper("antall-virkedager", new CustomHelpers.AntallVirkedagerMellomToDatoer());

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
        var myFieldValueResolver = new FieldValueResolver() {
            @Override
            protected Set<FieldWrapper> members(Class<?> clazz) {
                var members = super.members(clazz);
                return members.stream().filter(this::isValidField).collect(Collectors.toSet());
            }

            private boolean isValidField(FieldWrapper fw) {
                if (fw instanceof AccessibleObject) {
                    return isUseSetAccessible(fw);
                }
                return true;
            }
        };
        return Context.newBuilder(model)
            .resolver(JsonNodeValueResolver.INSTANCE,
                JavaBeanValueResolver.INSTANCE,
                myFieldValueResolver,
                MapValueResolver.INSTANCE,
                MethodValueResolver.INSTANCE)
            .build();
    }
}
