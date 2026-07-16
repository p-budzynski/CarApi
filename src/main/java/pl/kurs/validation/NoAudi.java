package pl.kurs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoAudiValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAudi {

    String message() default "AUDI?!?! No way! The equality parade has already passed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
