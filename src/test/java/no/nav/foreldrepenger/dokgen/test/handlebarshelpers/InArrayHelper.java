package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.io.IOException;
import java.util.List;

import org.assertj.core.util.Lists;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class InArrayHelper implements Helper<Object> {

    /*
     * Block helper that renders the block if an caseArray has the
     * given `value`. Optionally specify an inverse block to render
     * when the caseArray does not have the given value.
     *
     * ```handlebars
     * <!-- caseArray: ['a', 'b', 'c'] -->
     * {{#in-array caseArray "d"}}
     *   foo
     * {{else}}
     *   bar
     * {{/in-array}}
     * <!-- results in: 'bar' -->
     * ```
     */
    @Override
    public Object apply(Object caseArray, Options options) throws IOException {
        if (caseArray instanceof Iterable) {
            var param = options.params[0];
            if (((List) caseArray).contains(param)) {
                return options.fn();
            }
        }
        return options.inverse();
    }
}
