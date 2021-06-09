package no.nav.foreldrepenger.dokgen.test.support;

import java.math.BigDecimal;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class TrimDecimalHelper implements Helper<Double> {

    @Override
    public Object apply(Double decimal, Options options) {
        return BigDecimal.valueOf(decimal).stripTrailingZeros().toPlainString();
    }
}
