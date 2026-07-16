package pl.kurs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.kurs.repository.CarRepository;

@RequiredArgsConstructor
public class UniqueVinNumberValidator implements ConstraintValidator<UniqueVinNumber, String> {
    private final CarRepository carRepository;

    @Override
    public boolean isValid(String vinNumber, ConstraintValidatorContext context) {
        if (vinNumber == null || vinNumber.isBlank()) {
            return true;
        }

        return !carRepository.existsByVinNumber(vinNumber);
    }

}
