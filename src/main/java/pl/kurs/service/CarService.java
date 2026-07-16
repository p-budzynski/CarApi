package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.dto.CarDto;
import pl.kurs.entity.Car;
import pl.kurs.entity.Engine;
import pl.kurs.exception.CarNotFoundException;
import pl.kurs.mapper.CarMapper;
import pl.kurs.repository.CarRepository;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final EngineService engineService;
    private final CarMapper carMapper;

    @Transactional
    public Car addCar(Car car, Long engineId) {
        log.info("Adding new car: producer={}, model={}, vin={}",
                car.getProducer(),
                car.getModel(),
                car.getVinNumber());

        assignEngine(car, engineId);

        Car savedCar = carRepository.save(car);

        log.info("Car successfully added with id={}", savedCar.getId());

        return savedCar;
    }

    public Car getCarById(Long id) {
        log.info("Searching for car with id={}", id);

        return carRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Car with id={} does not exist", id);
                    return new CarNotFoundException("Car does not exist");
                });
    }

    @Transactional
    public void assignEngineToCar(Long id, Long engineId) {
        log.info("Assigning engine with id={} to car with id={}", engineId, id);

        Car car = getCarById(id);

        assignEngine(car, engineId);

        carRepository.save(car);

        log.info("Engine with id={} successfully assigned to car with id={}", engineId, id);
    }

    @Transactional
    public Car updateCar(Long id, CarDto carDto) {
        log.info("Updating car with id={}", id);

        Car car = getCarById(id);

        carMapper.updateCarFromDto(carDto, car);
        if (carDto.getEngineId() != null) {
            log.debug("Assigning engine with id={} to car id={}", carDto.getEngineId(), id);
            assignEngine(car, carDto.getEngineId());
        }

        log.info("Car with id={} updated successfully", id);
        return car;
    }

    public void deleteCarById(Long id) {
        log.info("Deleting car with id={}", id);

        carRepository.deleteById(id);

        log.info("Car with id={} successfully deleted", id);
    }

    private void assignEngine(Car car, Long engineId) {
        Engine engine = engineService.getEngineById(engineId);
        car.setEngine(engine);
    }

    public Set<Car> getOwnerCars(Long id) {
        log.info("Fetching cars for owner with id={}", id);

        Set<Car> cars = carRepository.findByOwnerId(id);

        log.debug("Found {} cars for owner with id={}", cars.size(), id);
        return cars;
    }
}
