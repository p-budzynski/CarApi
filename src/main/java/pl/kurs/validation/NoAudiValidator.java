package pl.kurs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoAudiValidator implements ConstraintValidator<NoAudi, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return !value.trim().equalsIgnoreCase("AUDI");
    }
}
