package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class NorwegianDatetimeHelper implements Helper<String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public Object apply(String s, Options options) throws IOException {
        return formatter.format((DateTimeFormatter.ISO_DATE_TIME.parse(s)));
    }
}
