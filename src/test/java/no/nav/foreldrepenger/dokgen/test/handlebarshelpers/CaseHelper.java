package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class CaseHelper implements Helper<Object> {

    private static final String CONDITION_VARIABLE = "__condition_variable";
    private static final String CONDITION_FULFILLED = "__condition_fulfilled";

    @Override
    public Object apply(Object caseKonstant, Options options) throws IOException {
        var konstant = options.hash.isEmpty() ? caseKonstant : options.hash;
        var model = (Map<String, Object>) options.context.model();
        var condition_variable = model.get(CONDITION_VARIABLE);
        if (caseKonstant instanceof Iterable) {
            if (((List) caseKonstant).contains(condition_variable)) {
                incrementConditionFulfilledCounter(model);
                return options.fn();
            }
        } else if (konstant.equals(condition_variable)) {
            incrementConditionFulfilledCounter(model);
            return options.fn();
        }
        return options.inverse();
    }

    private void incrementConditionFulfilledCounter(final Map<String, Object> model) {
        var antall = (Integer) model.get(CONDITION_FULFILLED);
        model.put(CONDITION_FULFILLED, ++antall);
    }
}
