package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class ThousandSeperatorHelperDouble implements Helper<Double> {
    @Override
    public Object apply(Double kroner, final Options options) {
        return new DecimalFormat(",###.##", new DecimalFormatSymbols(Locale.of("NO"))).format(kroner);
    }
}
