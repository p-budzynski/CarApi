package pl.kurs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.kurs.repository.CarRepository;

@RequiredArgsConstructor
public class UniqueRegistrationNumberValidator implements ConstraintValidator<UniqueRegistrationNumber, String> {
    private final CarRepository carRepository;

    @Override
    public boolean isValid(String registrationNumber, ConstraintValidatorContext context) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            return true;
        }

        return !carRepository.existsByRegistrationNumber(registrationNumber);
    }

}
