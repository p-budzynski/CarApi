package pl.kurs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.kurs.service.CarBrandService;

@RequiredArgsConstructor
public class CarBrandValidator implements ConstraintValidator<ValidCarBrand, String> {

    private final CarBrandService carBrandService;

    @Override
    public boolean isValid(String brand, ConstraintValidatorContext context) {
        if (brand == null || brand.isBlank()) return true;

        return carBrandService.exists(brand);
    }
}
