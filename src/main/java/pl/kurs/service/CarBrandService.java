package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kurs.repository.CarBrandRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarBrandService {

    private final CarBrandRepository carBrandRepository;

    public boolean exists(String brand) {
        String normalizedBrand = brand.trim();

        boolean exists = carBrandRepository.existsByNameIgnoreCase(normalizedBrand);

        log.debug("Checking if car brand '{}' exists. Result: {}", normalizedBrand, exists);

        return exists;
    }

}
