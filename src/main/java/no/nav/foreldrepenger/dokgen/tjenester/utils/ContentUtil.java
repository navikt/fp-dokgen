package no.nav.foreldrepenger.dokgen.tjenester.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

import no.nav.foreldrepenger.dokgen.tjenester.generator.DokStyling;
import no.nav.foreldrepenger.dokgen.tjenester.exceptions.DokgenNotFoundException;

public class ContentUtil {

    private static final Path TEMPLATE_BASE_PATH = Path.of("/content/");

    private ContentUtil() {
    }

    public static Path hentPathForMal(String malNavn, String språkKode) {
        return TEMPLATE_BASE_PATH.resolve("templates/%s/template_%s.hbs".formatted(malNavn, språkKode));
    }

    public static Path hentSchemaPathForMal(String malNavn) {
        return TEMPLATE_BASE_PATH.resolve("templates/%s/schema.json".formatted(malNavn));
    }

    public static Path hentHeaderPathFor(DokStyling format) {
        return TEMPLATE_BASE_PATH.resolve("formats/%s/header.html".formatted(format.name().toLowerCase()));
    }

    public static Path hentFooterPathFor(DokStyling format) {
        return TEMPLATE_BASE_PATH.resolve("formats/%s/footer.html".formatted(format.name().toLowerCase()));
    }

    public static Path hentCssPathFor(DokStyling format) {
        return TEMPLATE_BASE_PATH.resolve("formats/%s/style.css".formatted(format.name().toLowerCase()));
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
