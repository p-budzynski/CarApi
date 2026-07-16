package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
