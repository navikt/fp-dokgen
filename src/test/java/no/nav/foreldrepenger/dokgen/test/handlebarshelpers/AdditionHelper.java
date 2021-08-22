package no.nav.foreldrepenger.dokgen.test.handlebarhelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class AdditionHelper implements Helper<Integer> {

    @Override
    public Object apply(final Integer leftOperand, final Options options){
        return leftOperand + (Integer) options.param(0);
    }
}
