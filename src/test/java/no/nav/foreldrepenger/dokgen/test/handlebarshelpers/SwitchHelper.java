package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class SwitchHelper implements Helper<Object> {

    public Object apply(Object variable, Options options) throws IOException {
        List<String> variabelNavn = new ArrayList<>();
        List<Object> variabelVerdier = new ArrayList<>();
        variabelNavn.add("__condition_fulfilled");
        variabelVerdier.add(0);
        variabelNavn.add("__condition_variable");
        variabelVerdier.add(options.hash.isEmpty() ? variable : options.hash);
        var ctx = Context.newBlockParamContext(options.context, variabelNavn, variabelVerdier);
        var resultat = options.fn.apply(ctx);

        var antall = (Integer) ctx.get("__condition_fulfilled");
        if (Integer.valueOf(1).equals(antall)) {
            return resultat;
        }
        return null;
    }
}
