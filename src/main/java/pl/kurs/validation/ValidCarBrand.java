package pl.kurs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CarBrandValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCarBrand {

    String message() default "Invalid car brand";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
