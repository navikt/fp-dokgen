package content.support;

import no.nav.foreldrepenger.dokgen.tjenester.utils.ContentUtil;

import java.nio.file.Path;

final class TestContentUtil {

    private static final Path CONTENT_ROOT = ContentUtil.CONTENT_BASE_PATH;
    private static final Path TESTDATA_ROOT = Path.of("/testdata/");

    static Path hentPathForMal(BrevMal brevMal, String underMal, Språk språk) {
        return CONTENT_ROOT.resolve(String.format("templates/%s/%s_%s.hbs", brevMal.getNavn(), underMal, språk.getKode()));
    }

    static Path getTestDataPath(BrevMal brevMal, String testDataName) {
        return TESTDATA_ROOT.resolve(String.format("%s/input/%s.json", brevMal.getNavn(), testDataName));
    }

    static Path getTestDataPath(BrevMal brevMal, String underMal, String testDataName) {
        return TESTDATA_ROOT.resolve(String.format("%s/input/%s/%s.json", brevMal.getNavn(), underMal, testDataName));
    }

    static Path getExpectedPath(BrevMal brevMal, String expectedFileName) {
        return TESTDATA_ROOT.resolve(String.format("%s/expected/%s", brevMal.getNavn(), expectedFileName));
    }

    static Path getExpectedPath(BrevMal brevMal, String underMal, String expectedFileName) {
        return TESTDATA_ROOT.resolve(String.format("%s/expected/%s/%s", brevMal.getNavn(), underMal, expectedFileName));
    }
}
