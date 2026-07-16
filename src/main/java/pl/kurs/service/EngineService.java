package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.dto.EngineDto;
import pl.kurs.entity.Engine;
import pl.kurs.exception.EngineCannotBeRemovedException;
import pl.kurs.exception.EngineNotFoundException;
import pl.kurs.mapper.EngineMapper;
import pl.kurs.repository.CarRepository;
import pl.kurs.repository.EngineRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EngineService {

    private final EngineRepository engineRepository;
    private final CarRepository carRepository;
    private final EngineMapper engineMapper;

    public Engine getEngineById(Long id) {
        log.info("Searching for engine with id={}", id);

        return engineRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Engine with id={} does not exist", id);
                    return new EngineNotFoundException("Engine does not exists");
                });
    }

    public Engine addedEngine(Engine engine) {
        log.info("Adding new engine: model={}, capacity={}",
                engine.getModel(),
                engine.getCapacity());

        Engine savedEngine = engineRepository.save(engine);

        log.info("Engine successfully added with id={}", savedEngine.getId());

        return savedEngine;
    }

    @Transactional
    public Engine updateEngine(Long id, EngineDto engineDto) {

        Engine engine = getEngineById(id);

        engineMapper.updateEngineFromDto(engineDto, engine);
        return engine;
    }

    public void deleteEngineById(Long id) {
        log.info("Deleting engine with id={}", id);

        if (carRepository.existsByEngineId(id)) {
            log.warn("Cannot delete engine with id={} because it is assigned to a car", id);
            throw new EngineCannotBeRemovedException(
                    "The engine cannot be removed because it is assigned to a car"
            );
        }

        engineRepository.deleteById(id);

        log.info("Engine with id={} successfully deleted", id);
    }
}
