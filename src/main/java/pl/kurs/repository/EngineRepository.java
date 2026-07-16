package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.entity.Engine;

public interface EngineRepository extends JpaRepository<Engine, Long> {

}
