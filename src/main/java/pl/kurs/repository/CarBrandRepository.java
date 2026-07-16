package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.CarBrand;


public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    boolean existsByNameIgnoreCase(String name);
}
