package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.dto.CarDto;
import pl.kurs.entity.Car;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByEngineId(Long id);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByVinNumber(String vinNumber);

    Set<Car> findByOwnerId(Long id);

}
