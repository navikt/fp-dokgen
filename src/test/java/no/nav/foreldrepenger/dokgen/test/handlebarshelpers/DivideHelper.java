package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class DivideHelper implements Helper<Integer> {

    @Override
    public Object apply(final Integer antall, final Options options) {
        Integer beløp = options.param(0);
        BigDecimal value = BigDecimal.valueOf(beløp).divide(BigDecimal.valueOf(antall), 0, RoundingMode.HALF_UP);

        return value.intValue();
    }
}
