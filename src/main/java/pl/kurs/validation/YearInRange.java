package pl.kurs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearInRangeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface YearInRange {

    String message() default "Production year must be between 1980 and the current year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
