package no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.DokumentCssStyling;
import no.nav.foreldrepenger.dokgen.tjenester.dokumentgenerator.exceptions.DokgenNotFoundException;

public class ContentUtil {

    public static final Path CONTENT_BASE_PATH = Path.of("/content/");

    private ContentUtil() {
        // Utility class.
    }

    public static Path hentPathForMal(String malNavn) {
        return CONTENT_BASE_PATH.resolve("templates/%s/template.hbs".formatted(malNavn));
    }

    public static Path hentPathForMal(String malNavn, String språkKode) {
        return CONTENT_BASE_PATH.resolve("templates/%s/template_%s.hbs".formatted(malNavn, språkKode));
    }

    public static Path hentSchemaPathForMal(String malNavn) {
        return CONTENT_BASE_PATH.resolve("templates/%s/schema.json".formatted(malNavn));
    }

    public static Path hentHeaderPathFor(DokumentCssStyling styling) {
        return CONTENT_BASE_PATH.resolve("styling/%s/header.html".formatted(styling));
    }

    public static Path hentFooterPathFor(DokumentCssStyling styling) {
        return CONTENT_BASE_PATH.resolve("styling/%s/footer.html".formatted(styling));
    }

    public static Path hentCssPathFor(DokumentCssStyling styling) {
        return CONTENT_BASE_PATH.resolve("styling/%s/style.css".formatted(styling));
    }

    public static Path hentFontDirectoryPath() {
        return CONTENT_BASE_PATH.resolve("fonts/");
    }

    public static String lesRessursSomString(Path ressursPath) {
        return new String(lesRessursFra(ressursPath), StandardCharsets.UTF_8);
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
}
