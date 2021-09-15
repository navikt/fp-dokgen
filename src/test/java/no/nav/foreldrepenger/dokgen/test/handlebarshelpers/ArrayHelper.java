package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class ArrayHelper implements Helper<Object> {

    /*
     * Block helper that gives a possibility to define a
     * array in a handlebars template. It can be used as
     * an input parameter to in-array function.
     *
     * ```handlebars
     * <!-- array: ['a', 'b', 'c'] -->
     * {{#in-array (array 'a', 'b', 'c') 'd'}}
     *   foo
     * {{else}}
     *   bar
     * {{/in-array}}
     * <!-- results in: 'bar' -->
     * ```
     */
    @Override
    public Object apply(Object verdi, Options options) throws IOException {
        List<Object> verdier = new ArrayList<>();
        if (options.hash.isEmpty()) {
            verdier.add(verdi);
            verdier.addAll(Arrays.asList(options.params));
        } else {
            verdier.add(options.hash);
        }
        return verdier;
    }
}
