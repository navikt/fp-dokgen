package no.nav.foreldrepenger.fpdokgen.server;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public final class RequestValidator {

    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    private RequestValidator() {
    }

    public static <T> T validate(T candidate) {
        var violations = VALIDATOR.validate(candidate);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return candidate;
    }
}
