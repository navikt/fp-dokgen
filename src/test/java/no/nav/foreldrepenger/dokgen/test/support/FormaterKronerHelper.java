package no.nav.foreldrepenger.dokgen.test.support;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class FormaterKronerHelper implements Helper<Integer> {
    @Override
    public Object apply(Integer kroner, final Options options) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(kroner.intValue());
    }
}
