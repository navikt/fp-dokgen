package no.nav.foreldrepenger.dokgen.tjenester;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;

class DokumentGeneratorRequestTest {

    @Test
    void skal_ha_alle_enum_verdier_for_Språk() {
        assertThat(DokumentGeneratorRequest.Språk.values())
            .containsExactly(
                DokumentGeneratorRequest.Språk.BOKMÅL,
                DokumentGeneratorRequest.Språk.NYNORSK,
                DokumentGeneratorRequest.Språk.ENGELSK
            );
    }

    @Test
    void skal_ha_alle_enum_verdier_for_CssStyling() {
        assertThat(DokumentGeneratorRequest.CssStyling.values())
            .containsExactly(
                DokumentGeneratorRequest.CssStyling.PDF,
                DokumentGeneratorRequest.CssStyling.HTML,
                DokumentGeneratorRequest.CssStyling.INNTEKTSMELDING_PDF
            );
    }

    @Test
    void malNavn_felt_skal_ha_NotNull_og_FormParam_annotasjoner() throws NoSuchFieldException {
        Field field = DokumentGeneratorRequest.class.getDeclaredField("malNavn");

        assertThat(field.isAnnotationPresent(NotNull.class)).isTrue();
        assertThat(field.isAnnotationPresent(FormParam.class)).isTrue();
        assertThat(field.getAnnotation(FormParam.class).value()).isEqualTo("malNavn");
    }

    @Test
    void språk_felt_skal_ha_FormParam_annotasjon() throws NoSuchFieldException {
        Field field = DokumentGeneratorRequest.class.getDeclaredField("språk");

        assertThat(field.isAnnotationPresent(FormParam.class)).isTrue();
        assertThat(field.getAnnotation(FormParam.class).value()).isEqualTo("språk");
        assertThat(field.isAnnotationPresent(NotNull.class)).isFalse();
    }

    @Test
    void cssStyling_felt_skal_ha_NotNull_FormParam_og_DefaultValue_annotasjoner() throws NoSuchFieldException {
        Field field = DokumentGeneratorRequest.class.getDeclaredField("cssStyling");

        assertThat(field.isAnnotationPresent(NotNull.class)).isTrue();
        assertThat(field.isAnnotationPresent(FormParam.class)).isTrue();
        assertThat(field.isAnnotationPresent(DefaultValue.class)).isTrue();
        assertThat(field.getAnnotation(FormParam.class).value()).isEqualTo("cssStyling");
        assertThat(field.getAnnotation(DefaultValue.class).value()).isEqualTo("PDF");
    }

    @Test
    void inputData_felt_skal_ha_NotNull_og_FormParam_annotasjoner() throws NoSuchFieldException {
        Field field = DokumentGeneratorRequest.class.getDeclaredField("inputData");

        assertThat(field.isAnnotationPresent(NotNull.class)).isTrue();
        assertThat(field.isAnnotationPresent(FormParam.class)).isTrue();
        assertThat(field.getAnnotation(FormParam.class).value()).isEqualTo("inputData");
    }

    @Test
    void skal_kunne_parse_Språk_enum_fra_string() {
        assertThat(DokumentGeneratorRequest.Språk.valueOf("BOKMÅL")).isEqualTo(DokumentGeneratorRequest.Språk.BOKMÅL);
        assertThat(DokumentGeneratorRequest.Språk.valueOf("NYNORSK")).isEqualTo(DokumentGeneratorRequest.Språk.NYNORSK);
        assertThat(DokumentGeneratorRequest.Språk.valueOf("ENGELSK")).isEqualTo(DokumentGeneratorRequest.Språk.ENGELSK);
    }

    @Test
    void skal_kunne_parse_CssStyling_enum_fra_string() {
        assertThat(DokumentGeneratorRequest.CssStyling.valueOf("PDF")).isEqualTo(DokumentGeneratorRequest.CssStyling.PDF);
        assertThat(DokumentGeneratorRequest.CssStyling.valueOf("HTML")).isEqualTo(DokumentGeneratorRequest.CssStyling.HTML);
        assertThat(DokumentGeneratorRequest.CssStyling.valueOf("INNTEKTSMELDING_PDF")).isEqualTo(DokumentGeneratorRequest.CssStyling.INNTEKTSMELDING_PDF);
    }
}
