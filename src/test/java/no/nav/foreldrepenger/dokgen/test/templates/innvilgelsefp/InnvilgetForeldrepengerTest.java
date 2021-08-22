package no.nav.foreldrepenger.dokgen.test.templates.innvilgelsefp;

import no.nav.foreldrepenger.dokgen.test.support.TemplateTestService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InnvilgetForeldrepengerTest {

    private static final String TEMPLATE_NAME = "innvilget-foreldrepenger";
    private static final String TEMPLATE_PATH = "/template_";

    @Test
    public void førstegangsbehandling_uten_gradering_og_avslag_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/automatisk_ingen_gradering_ingen_avslag");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME,  "forstegangsbehandling/forstegangsbehandling_uten_gradering_avslag_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_avslag_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/med_avslag_periode");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME,  "forstegangsbehandling/forstegangsbehandling_med_avslag_periode_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_fritekst_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/med_fritekst");
        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME,  "forstegangsbehandling/forstegangsbehandling_med_fritekst_nb.txt"));
    }

    @Test
    public void førstegangsbehandling_med_flere_arbeidsgivere_og_gradering_nb() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "forstegangsbehandling/gradering_flere_arbgivere_og_gradering");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME,  "forstegangsbehandling/forstegangsbehandling_gradering_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_uttak() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_endret_endring_i_uttak");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_endret_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_innvilget_endring_i_uttak() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_innvilget_endring_i_uttak");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_innvilget_endring_i_uttak_nb.txt"));
    }

    @Test
    public void revurdering_foreldrepenger_endret_endring_i_beregning() throws Exception {
        // Act
        String resultat = TemplateTestService.compile(TEMPLATE_NAME, TEMPLATE_PATH, "nb", "revurdering/foreldrepenger_endret_endring_i_beregning");

        // Assert
        assertThat(resultat).isEqualToIgnoringWhitespace(TemplateTestService.getExpectedResult(TEMPLATE_NAME, "revurdering/revurdering_foreldrepenger_endret_endring_i_beregning_nb.txt"));
    }

}
