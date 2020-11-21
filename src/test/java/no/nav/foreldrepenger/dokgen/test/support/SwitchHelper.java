package no.nav.foreldrepenger.dokgen.test.support;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwitchHelper implements Helper<Object> {

    public Object apply(Object variable, Options options) throws IOException {
        List<String> variabelNavn = new ArrayList<>();
        List<Object> variabelVerdier = new ArrayList<>();
        variabelNavn.add("__condition_fulfilled");
        variabelVerdier.add(0);
        variabelNavn.add("__condition_variable");
        variabelVerdier.add(options.hash.isEmpty() ? variable : options.hash);
        Context ctx = Context.newBlockParamContext(options.context, variabelNavn, variabelVerdier);
        String resultat = options.fn.apply(ctx);


        Integer antall = (Integer) ctx.get("__condition_fulfilled");
        if (Integer.valueOf(1).equals(antall)) {
            return resultat;
        }
        throw new IllegalArgumentException("Switch-case må treffe i 1 case, men traff i " + antall + " med verdien " + ctx.get("__condition_variable"));
    }
}
