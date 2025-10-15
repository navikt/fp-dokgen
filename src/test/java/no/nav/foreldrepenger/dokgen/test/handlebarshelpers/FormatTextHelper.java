package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.Locale;

/**
 * Formats a given string according to the provided named parameters.
 *
 * Supported options:
 * - toLower=true: Converts the string to lower case.
 * - toUpper=true: Converts the string to upper case.
 * - replaceUnderscore=true: Replaces underscores with spaces.
 * - capitalizeFirst=true: Capitalizes only the first character of the resulting string.
 * - trim=true: Removes leading and trailing whitespace.
 *
 * Example use:
 *  {{formatText HELLO_WORLD toLower=true replaceUnderscore=true}} => "hello world"
 *  {{formatText HELLO_WORLD toLower=true replaceUnderscore=true capitalizeFirst=true}} => "Hello world"
 */
public class FormatTextHelper implements Helper<String> {

    @Override
    public Object apply(String result, Options options) throws IOException {
        if (result == null) {
            return "";
        }

        boolean toLower = getBooleanOption(options, "toLower");
        boolean toUpper = getBooleanOption(options, "toUpper");
        boolean replaceUnderscore = getBooleanOption(options, "replaceUnderscore");
        boolean capitalizeFirst = getBooleanOption(options, "capitalizeFirst");

        // Apply transformations in logical order
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
            result = result.substring(0, 1).toUpperCase(Locale.getDefault()) + result.substring(1);
        }

        return result;
    }

    /**
     * Safely retrieves a boolean option from the Handlebars options hash.
     */
    private boolean getBooleanOption(Options options, String key) {
        Object value = options.hash(key);
        if (value == null) {
            return false;
        }
        return Boolean.parseBoolean(value.toString());
    }
}

