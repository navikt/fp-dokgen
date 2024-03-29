package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class SizeHelper implements Helper<Object> {

    public Object apply(Object o, Options options) {
        if (o instanceof ArrayNode) {
            return ((ArrayNode) o).size();
        }
        return 0;
    }
}
