package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.Map;

public class CaseHelper implements Helper<Object> {
    @Override
    public Object apply(Object caseKonstant, Options options) throws IOException {
        Object konstant = options.hash.isEmpty() ? caseKonstant : options.hash;
        Map<String, Object> model = (Map<String, Object>) options.context.model();
        Object condition_variable = model.get("__condition_variable");
        if (konstant.equals(condition_variable)) {
            Integer antall = (Integer) model.get("__condition_fulfilled");
            model.put("__condition_fulfilled", ++antall);
            return options.fn();
        }
        return options.inverse();
    }
}
