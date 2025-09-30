package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class ArbeidsforholdLookupHelper implements Helper<Object> {

    /**
     * Lookup helper to find an arbeidsforhold in a list by orgnummer.
     * Usage:
     * {{#arbeidsforholdLookup orgnummer arbeidsforholdListe}}
     *   {{navn}} - {{stillingsprosent}} {{orgnummer}}
     * {{/arbeidsforholdLookup}}
     */
    @Override
    public Object apply(Object context, Options options) throws IOException {
        if (!(context instanceof String orgnummer)) {
            return "";
        }

        var param0 = options.param(0, null);
        if (!(param0 instanceof JsonNode list)) {
            return "(" + orgnummer + ")";
        }

        for (var node : list) {
            var orgNode = node.get("orgnummer");
            if (orgNode != null && orgnummer.equals(orgNode.asText())) {
                return options.fn(node);
            }
        }

        return "(" + orgnummer + ")";
    }
}
