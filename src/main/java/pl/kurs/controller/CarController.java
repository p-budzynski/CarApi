package pl.kurs.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.CarDto;
import pl.kurs.entity.Car;
import pl.kurs.mapper.CarMapper;
import pl.kurs.service.CarService;
import pl.kurs.validation.Create;
import pl.kurs.validation.Update;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(carMapper.entityToDto(car));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto addCar(@RequestBody @Validated(Create.class) CarDto carDto) {
        Car car = carMapper.dtoToEntity(carDto);
        Car addedCar = carService.addCar(car, carDto.getEngineId());
        return carMapper.entityToDto(addedCar);
    }

    @PutMapping("/{id}/engines/{engineId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignEngineToCar(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id,
                                  @PathVariable("engineId") @Min(value = 1, message = "ID must be greater than zero!") Long engineId) {
        carService.assignEngineToCar(id, engineId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id,
                                            @RequestBody @Validated(Update.class) CarDto carDto) {
        Car updatedCar = carService.updateCar(id, carDto);
        return ResponseEntity.ok(carMapper.entityToDto(updatedCar));
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        carService.deleteCarById(id);
    }
}
