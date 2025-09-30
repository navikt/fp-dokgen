package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class NorwegianDateHelper implements Helper<String> {

    /**
     * Allows converting ISO8601 to be formatted as a Norwegian dd.MM.yyyy string.
     *
     * Usage:
     * {{norwegian-date 2020-02-01}} prints 01.02.2020
     */
    @Override
    public Object apply(String isoFormattedDate, Options options) {
        if (isoFormattedDate == null || isoFormattedDate.isBlank()) {
            return "";
        }

        // Expecting ISO format yyyy-MM-dd
        String[] parts = isoFormattedDate.split("-");
        if (parts.length != 3) {
            return isoFormattedDate; // fallback: return unchanged if unexpected format
        }

        return parts[2] + "." + parts[1] + "." + parts[0];
    }
}
