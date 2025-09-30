package no.nav.foreldrepenger.dokgen.test.handlebarshelpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.neovisionaries.i18n.CountryCode;

import java.io.IOException;
import java.util.Locale;

public class CountryCodeHelper implements Helper<Object> {

    /**
     * Can accept country codes in alpha2 or alpha3 format.
     * Example: NO or NOR -> Norge
     */
    @Override
    public Object apply(Object landKode, Options options) throws IOException {
        if (!(landKode instanceof String code) || code.isBlank()) {
            return "";
        }

        var country = CountryCode.getByCode(code);
        if (country == null) {
            return code; // fallback if not found
        }

        var locale = new Locale.Builder()
                .setLanguage("no")
                .setRegion(country.getAlpha2())
                .build();

        return locale.getDisplayCountry(Locale.forLanguageTag("no"));
    }
}
