package pl.kurs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VinNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface VinNumber {

    String message() default "Invalid VIN number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
