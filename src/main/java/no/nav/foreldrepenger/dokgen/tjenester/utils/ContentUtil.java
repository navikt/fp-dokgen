package no.nav.foreldrepenger.dokgen.tjenester.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

import no.nav.foreldrepenger.dokgen.tjenester.exceptions.DokgenNotFoundException;
import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;

public class ContentUtil {

    public static final Path TEMPLATE_BASE_PATH = Path.of("/content/");

    private ContentUtil() {
    }

    public static Path hentPathForMal(String malNavn) {
        return TEMPLATE_BASE_PATH.resolve("templates/%s/template.hbs".formatted(malNavn));
    }

    public static Path hentPathForMal(String malNavn, String språkKode) {
        return TEMPLATE_BASE_PATH.resolve("templates/%s/template_%s.hbs".formatted(malNavn, språkKode));
    }

    public static Path hentSchemaPathForMal(String malNavn) {
        return TEMPLATE_BASE_PATH.resolve("templates/%s/schema.json".formatted(malNavn));
    }

    public static Path hentHeaderPathFor(DokStyling styling) {
        return TEMPLATE_BASE_PATH.resolve("styling/%s/header.html".formatted(styling));
    }

    public static Path hentFooterPathFor(DokStyling styling) {
        return TEMPLATE_BASE_PATH.resolve("styling/%s/footer.html".formatted(styling));
    }

    public static Path hentCssPathFor(DokStyling styling) {
        return TEMPLATE_BASE_PATH.resolve("styling/%s/style.css".formatted(styling));
    }

    public static Path hentFontDirectoryPath() {
        return TEMPLATE_BASE_PATH.resolve("fonts/");
    }

    public static byte[] lesRessursFra(Path ressursPath) {
        Objects.requireNonNull(ressursPath, "ressursPath");
        var ressursNavn = ressursPath.toString();
        try (var is = ContentUtil.class.getResourceAsStream(ressursNavn)) {
            if (is == null) {
                throw new DokgenNotFoundException("Finner ikke ressurs: " + ressursNavn);
            }
            return is.readAllBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Kunne ikke laste ressurs: " + ressursNavn, e);
        }
    }

    public static String lesRessursSomString(Path ressursPath) {
        return new String(lesRessursFra(ressursPath), StandardCharsets.UTF_8);
    }
}
