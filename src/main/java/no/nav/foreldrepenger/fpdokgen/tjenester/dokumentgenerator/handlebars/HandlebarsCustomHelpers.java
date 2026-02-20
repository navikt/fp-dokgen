package no.nav.foreldrepenger.fpdokgen.tjenester.dokumentgenerator.handlebars;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.neovisionaries.i18n.CountryCode;

/**
 * Custom Handlebars helpers for document generation.
 */
final class HandlebarsCustomHelpers {

    private HandlebarsCustomHelpers() {
        // Utility class
    }

    /**
     * Allows using switch/case in hbs templates
     * <p>
     * Syntax:
     * {{#switch variable}}
     * {{#case "SOME_VALUE"}}
     * <p>Content that should display if variable=="SOME_VALUE"</p>
     * {{/case}}
     * {{#case "SOME_OTHER_VALUE"}}
     * <p>Content that should display if variable=="SOME_OTHER_VALUE"</p>
     * {{/case}}
     * {{/switch}}
     */
    static class SwitchHelper implements Helper<Object> {

        @Override
        public Object apply(Object variable, Options options) throws IOException {
            List<String> variabelNavn = new ArrayList<>();
            List<Object> variabelVerdier = new ArrayList<>();
            variabelNavn.add("__condition_fulfilled");
            variabelVerdier.add(0);
            variabelNavn.add("__condition_variable");
            variabelVerdier.add(options.hash.isEmpty() ? variable : options.hash);
            var ctx = Context.newBlockParamContext(options.context, variabelNavn, variabelVerdier);
            String resultat = options.fn.apply(ctx);

            var antall = (Integer) ctx.get("__condition_fulfilled");
            if (Integer.valueOf(1).equals(antall)) {
                return resultat;
            }
            return null;
        }
    }

    /**
     * @see SwitchHelper
     */
    static class CaseHelper implements Helper<Object> {
        private static final String CONDITION_VARIABLE = "__condition_variable";
        private static final String CONDITION_FULFILLED = "__condition_fulfilled";

        @Override
        @SuppressWarnings("unchecked")
        public Object apply(Object caseKonstant, Options options) throws IOException {
            var konstant = options.hash.isEmpty() ? caseKonstant : options.hash;
            var model = (Map<String, Object>) options.context.model();
            var conditionVariable = model.get(CONDITION_VARIABLE);

            if (caseKonstant instanceof Iterable<?> iterable) {
                var list = (List<?>) iterable;
                if (list.contains(conditionVariable)) {
                    incrementConditionFulfilledCounter(model);
                    return options.fn();
                }
            } else if (konstant.equals(conditionVariable)) {
                incrementConditionFulfilledCounter(model);
                return options.fn();
            }
            return options.inverse();
        }

        private void incrementConditionFulfilledCounter(Map<String, Object> model) {
            var antall = (Integer) model.get(CONDITION_FULFILLED);
            model.put(CONDITION_FULFILLED, antall + 1);
        }
    }

    /**
     * Allows to create a table with a set number of columns from only td cells.
     * Useful if you have to render table cells but you don't know how many cells you will have.
     * <p>
     * Example:
     * {{#table [columns=2] [class=""]]}}
     * <td>This is one cell</td>
     * <td>This is another cell</td>
     * <td>This is a third cell</td>
     * {{/table}}
     * <p>
     * will render a table with two tr rows with two cells in each
     */
    static class TableHelper implements Helper<Object> {

        @Override
        public Object apply(Object context, Options options) throws IOException {
            int columnCount = options.hash("columns", 2);
            var tableContents = options.fn(context).toString();
            var cells = Arrays.stream(tableContents.trim().split("</td>")).filter(s -> !s.isEmpty()).map(s -> s + "</td>").toList();

            var wrappedInRows = new StringBuilder("<tr>");
            for (int index = 0; index < cells.size(); index++) {
                if (index > 0 && index % columnCount == 0) {
                    wrappedInRows.append("</tr><tr>");
                }
                wrappedInRows.append(cells.get(index));
            }

            if (cells.size() % columnCount > 0) {
                int missingCellsInLastRow = columnCount - (cells.size() % columnCount);
                wrappedInRows.append("<td></td>".repeat(missingCellsInLastRow));
            }

            wrappedInRows.append("</tr>");

            String classParam = options.hash("class", "");
            var classString = classParam.isEmpty() ? "" : "class=" + classParam;
            return "<table %s>%s</table>".formatted(classString, wrappedInRows);
        }
    }

    /**
     * Allows simple addition inside a template
     * <p>
     * {{add 3 4}} prints 7 for example.
     */
    static class AdditionHelper implements Helper<Integer> {
        @Override
        public Object apply(Integer leftOperand, Options options) {
            return leftOperand + options.<Integer>param(0);
        }
    }

    /**
     * Allows converting ISO8601 to be formatted as a norwegian dd.mm.yyyy string
     * <p>
     * {{norwegian-date 2020-02-01}} prints 01.02.2020
     */
    static class NorwegianDateHelper implements Helper<String> {
        @Override
        public Object apply(String isoFormattedDate, Options options) {
            var parts = isoFormattedDate.split("-");
            return parts[2] + "." + parts[1] + "." + parts[0];
        }
    }

    /**
     * Parses ISO-8601 extended local or offset date-time format, and returns a string in dd.mm.yyyy HH:mm format
     * <p>
     * {{norwegian-datetime 2019-08-19T15:54:01}} prints 19.08.2019 15:54
     * {{norwegian-datetime "2019-08-19T15:54:01" includeSeconds=true}} → 19.08.2019 15:54:01
     */
    static class NorwegianDateTimeHelper implements Helper<String> {
        private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        private static final DateTimeFormatter DATETIME_SECOND_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        @Override
        public Object apply(String isoFormattedDateTime, Options options) {
            var dateTime = DateTimeFormatter.ISO_DATE_TIME.parse(isoFormattedDateTime);
            var includeSeconds = Boolean.TRUE.equals(options.hash("includeSeconds"));
            return includeSeconds ? DATETIME_SECOND_FORMAT.format(dateTime) : DATETIME_FORMAT.format(dateTime);
        }
    }

    /**
     * Allows to divide a template parameter with a given number and round the result value to the nearest krone
     */
    static class DivideHelper implements Helper<Integer> {
        @Override
        public Object apply(Integer antall, Options options) {
            int beløp = options.param(0);
            var value = BigDecimal.valueOf(beløp).divide(BigDecimal.valueOf(antall), 0, RoundingMode.HALF_UP);
            return value.intValue();
        }
    }

    /**
     * Format an int with thousand separator and make the space not breakable to prevent line breaks within an amount,
     * ex: 10000 will be 10 000
     * ex: 20000.45 will be 20 000.45
     * The function does not handle decimals
     */
    static class ThousandSeperatorHelper implements Helper<Number> {
        @Override
        public Object apply(Number tall, Options options) {
            if (tall instanceof Integer kroner) {
                return String.format(Locale.forLanguageTag("NO"), "%,d", kroner);
            }
            return new DecimalFormat(",###.##", new DecimalFormatSymbols(Locale.of("NO"))).format(tall);
        }
    }

    /**
     * Removes trailing zeroes from decimals, ex: 10.0 becomes 10 and 90.20 becomes 90.2, while 100.3 remains the same
     */
    static class TrimDecimalHelper implements Helper<Double> {
        @Override
        public Object apply(Double decimal, Options options) {
            return BigDecimal.valueOf(decimal).stripTrailingZeros().toPlainString();
        }
    }

    /**
     * Block helper that gives a possibility to define an
     * array in a handlebars template. It can be used as
     * an input parameter to in-array function.
     * <p>
     * ```handlebars
     * {{#in-array (array 'a', 'b', 'c') 'd'}}
     * foo
     * {{else}}
     * bar
     * {{/in-array}}
     * <!-- results in: 'bar' -->
     * ```
     */
    static class ArrayHelper implements Helper<Object> {
        @Override
        public Object apply(Object verdi, Options options) throws IOException {
            List<Object> verdier = new ArrayList<>();
            if (options.hash.isEmpty()) {
                verdier.add(verdi);
                verdier.addAll(Arrays.asList(options.params));
            } else {
                verdier.add(options.hash);
            }
            return verdier;
        }
    }

    /**
     * Block helper that renders the block if an caseArray has the
     * given `value`. Optionally specify an inverse block to render
     * when the caseArray does not have the given value.
     * <p>
     * ```handlebars
     * {{#in-array caseArray "d"}}
     * foo
     * {{else}}
     * bar
     * {{/in-array}}
     * <!-- results in: 'bar' -->
     * ```
     */
    static class InArrayHelper implements Helper<Object> {
        @Override
        public Object apply(Object caseArray, Options options) throws IOException {
            if (caseArray instanceof Iterable<?> iterable) {
                var param = options.params[0];
                var list = (List<?>) iterable;
                if (list.contains(param)) {
                    return options.fn();
                }
            }
            return options.inverse();
        }
    }

    /**
     * Returns the size of an array
     */
    static class SizeHelper implements Helper<Object> {
        @Override
        public Object apply(Object o, Options options) {
            if (o instanceof Iterable<?> iterable) {
                int count = 0;
                for (var _ : iterable) {
                    count++;
                }
                return count;
            }
            return 0;
        }
    }

    /**
     * Accepts country codes in both alpha-2 and alpha-3 formats.
     * For example, both "NO" and "NOR" will return "Norge".
     * <p>
     * Optionally, you can specify a language using the `lang` parameter to get the country name in a different language.
     * Example: {{countryCode "NO" lang="en"}} will return "Norway".
     */
    static class CountryCodeHelper implements Helper<Object> {
        @Override
        public Object apply(Object landKode, Options options) {
            if (!(landKode instanceof String code) || code.isBlank()) {
                return "";
            }
            var langParam = options.hash.get("lang");
            var lang = langParam != null ? langParam.toString() : "no";
            return new Locale.Builder().setLanguage(lang)
                .setRegion(CountryCode.getByCode(code).getAlpha2())
                .build()
                .getDisplayCountry(Locale.forLanguageTag(lang));
        }
    }

    /**
     * Lookup helper to find an arbeidsforhold in a list by orgnummer
     * Usage:
     * {{#arbeidsforholdLookup arbeidsforholdListe orgnummer}}
     * {{navn}} - {{stillingsprosent}} {{orgnummer}}
     * {{/arbeidsforholdLookup}}
     */
    static class ArbeidsforholdLookupHelper implements Helper<Object> {
        @Override
        public Object apply(Object context, Options options) throws IOException {
            if (!(context instanceof String orgnummer)) {
                return "";
            }

            var param0 = options.param(0, null);
            if (!(param0 instanceof Iterable iterable)) {
                return "(" + orgnummer + ")";
            }
            for (var node : iterable) {
                if (!(node instanceof Map map)) {
                    return "(" + orgnummer + ")";
                }
                var orgNode = map.get("orgnummer");
                if (orgNode != null && orgnummer.equals(orgNode.toString())) {
                    return options.fn(node);
                }
            }

            return "(" + orgnummer + ")";
        }
    }

    /**
     * Counts the number of weekdays (Monday to Friday) between two dates, inclusive.
     * Example use: {{antallVirkedagerMellomToDatoer startDato sluttDato}}
     * DateTimeFormatter.ISO_LOCAL_DATE => "yyyy-MM-dd"
     */
    static class AntallVirkedagerMellomToDatoer implements Helper<String> {
        @Override
        public Object apply(String dato1, Options options) {
            var start = LocalDate.parse(dato1);
            var end = LocalDate.parse((String) options.params[0]);

            var from = start.isBefore(end) ? start : end;
            var to = start.isBefore(end) ? end : start;

            int count = 0;
            var date = from;
            while (!date.isAfter(to)) {
                if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    count++;
                }
                date = date.plusDays(1);
            }
            return count;
        }
    }

    /**
     * Formats a given string according to the provided named parameters.
     * <p>
     * Supported options:
     * - toLower=true: Converts the string to lower case.
     * - toUpper=true: Converts the string to upper case.
     * - replaceUnderscore=true: Replaces underscores with spaces.
     * - capitalizeFirst=true: Capitalizes only the first character of the resulting string.
     * <p>
     * Example use:
     * {{format-text HELLO_WORLD toLower=true replaceUnderscore=true}} => "hello world"
     * {{format-text HELLO_WORLD toLower=true replaceUnderscore=true capitalizeFirst=true}} => "Hello world"
     */
    static class FormatText implements Helper<String> {
        @Override
        public Object apply(String tekst, Options options) {
            if (tekst == null) {
                return "";
            }

            var result = tekst;

            var toLower = Boolean.parseBoolean(String.valueOf(options.hash.get("toLower")));
            var toUpper = Boolean.parseBoolean(String.valueOf(options.hash.get("toUpper")));
            var replaceUnderscore = Boolean.parseBoolean(String.valueOf(options.hash.get("replaceUnderscore")));
            var capitalizeFirst = Boolean.parseBoolean(String.valueOf(options.hash.get("capitalizeFirst")));

            if (replaceUnderscore) {
                result = result.replace('_', ' ');
            }
            if (toLower) {
                result = result.toLowerCase(Locale.getDefault());
            }
            if (toUpper) {
                result = result.toUpperCase(Locale.getDefault());
            }
            if (capitalizeFirst && !result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }

            return result;
        }
    }
}
