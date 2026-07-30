package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kurs.dto.CarDto;
import pl.kurs.entity.Car;

import java.util.Optional;
import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByEngineId(Long id);

    boolean existsByRegistrationNumber(String registrationNumber);

    boolean existsByVinNumber(String vinNumber);

    Set<Car> findByOwnerId(Long id);

    @Query("""
            SELECT c from Car c
            LEFT JOIN FETCH c.engine
            WHERE c.id = :id
            """)
    Optional<Car> findByIdWithEngine(@Param("id") Long id);
}
