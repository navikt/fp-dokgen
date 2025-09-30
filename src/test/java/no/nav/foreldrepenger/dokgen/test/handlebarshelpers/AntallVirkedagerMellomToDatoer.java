package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class AntallVirkedagerMellomToDatoer implements Helper<String> {

    /**
     * Counts the number of weekdays (Mon–Fri) between two dates, inclusive.
     * Example: {{antallVirkedagerMellomToDatoer startDato sluttDato}}
     * Dates must be in ISO format: yyyy-MM-dd
     */
    @Override
    public Object apply(String dato1, Options options) throws IOException {
        if (dato1 == null || options.param(0, null) == null) {
            return 0;
        }

        var start = LocalDate.parse(dato1);
        var end = LocalDate.parse(options.param(0, null).toString());

        var from = start.isBefore(end) ? start : end;
        var to = start.isBefore(end) ? end : start;

        int count = 0;
        var date = from;

        while (!date.isAfter(to)) {
            var dow = date.getDayOfWeek();
            if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) {
                count++;
            }
            date = date.plusDays(1);
        }

        return count;
    }
}
