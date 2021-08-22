package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class FormatKronerHelper implements Helper<Integer> {
    @Override
    public Object apply(Integer kroner, final Options options) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(kroner.intValue());
    }
}
