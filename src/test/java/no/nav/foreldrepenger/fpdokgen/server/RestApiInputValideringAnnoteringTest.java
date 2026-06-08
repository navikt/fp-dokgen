package no.nav.foreldrepenger.fpdokgen.server;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RestApiInputValideringAnnoteringTest extends RestApiTester {

    private static final String CONSTRAINT_PACKAGE = "jakarta.validation.constraints";

    /**
     * IKKE ignorer eller fjern denne testen, den sørger for at inputvalidering er i orden for REST-grensesnittene.
     * <p>
     * Etter migrering til Javalin: vi kan ikke lenger inspisere JAX-RS-parameterannoteringer. Vi går i stedet
     * gjennom DTO-klassene som brukes som handler-input og verifiserer at alle felter har minst én
     * validation-constraint-annotering. {@link RequestValidator} kjører i {@code ApiConfig} for hver request.
     */
    @Test
    void alle_felter_i_input_dto_skal_ha_validering_constraint() {
        var dtoClasses = collectInputDtoClasses();
        assertThat(dtoClasses).as("forventer minst én input-DTO registrert i ApiConfig").isNotEmpty();

        for (var dto : dtoClasses) {
            for (var field : dto.getDeclaredFields()) {
                var type = field.getType();
                if (type.isPrimitive() || type.isEnum()) {
                    continue;
                }
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                assertThat(harValideringConstraint(field.getAnnotations()))
                    .as("Feltet %s.%s må ha minst én jakarta.validation.constraints-annotering",
                        dto.getSimpleName(), field.getName())
                    .isTrue();
            }
        }
    }

    private static Set<Class<?>> collectInputDtoClasses() {
        var result = new LinkedHashSet<Class<?>>();
        for (var klasse : finnAlleRestTjenester()) {
            for (Method method : klasse.getDeclaredMethods()) {
                if (!java.lang.reflect.Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                for (var param : method.getParameterTypes()) {
                    if (isCandidateDto(param)) {
                        result.add(param);
                    }
                }
            }
        }
        return result;
    }

    private static boolean isCandidateDto(Class<?> type) {
        if (type.isPrimitive() || type.isEnum() || type.isInterface()) {
            return false;
        }
        return type.getName().startsWith("no.nav.foreldrepenger.fpdokgen");
    }

    private static boolean harValideringConstraint(Annotation[] annotations) {
        var annotationTypes = new HashSet<>(Arrays.asList(annotations));
        return annotationTypes.stream()
            .map(a -> a.annotationType().getName())
            .anyMatch(name -> name.startsWith(CONSTRAINT_PACKAGE));
    }
}
