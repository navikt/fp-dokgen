package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.util.Locale;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class ThousandSeperatorHelper implements Helper<Integer> {
    @Override
    public Object apply(Integer kroner, final Options options) {
        return String.format(Locale.forLanguageTag("NO"), "%,d", kroner);
    }
}
