package pl.kurs.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.CarDto;
import pl.kurs.dto.OwnerDto;
import pl.kurs.entity.Car;
import pl.kurs.entity.Owner;
import pl.kurs.mapper.CarMapper;
import pl.kurs.mapper.OwnerMapper;
import pl.kurs.service.CarService;
import pl.kurs.service.OwnerService;
import pl.kurs.validation.Create;
import pl.kurs.validation.Update;

import java.util.Set;

@Validated
@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;
    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        Owner owner = ownerService.getOwnerById(id);
        return ResponseEntity.ok(ownerMapper.entityToDto(owner));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto addOwner(@RequestBody @Validated(Create.class) OwnerDto ownerDto) {
        Owner owner = ownerMapper.dtoToEntity(ownerDto);
        Owner addedOwner = ownerService.addOwner(owner);
        return ownerMapper.entityToDto(addedOwner);
    }

    @PutMapping("/{id}/cars/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignCarToOwner(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id,
                                 @PathVariable("carId") @Min(value = 1, message = "ID must be greater than zero!") Long carId) {
        ownerService.assignCarToOwner(id, carId);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<Set<CarDto>> getOwnerCars(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        Set<Car> ownerCars = carService.getOwnerCars(id);
        return ResponseEntity.ok(carMapper.entitiesToDtos(ownerCars));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id,
                                                @RequestBody @Validated(Update.class) OwnerDto ownerDto) {
        Owner updatedOwner = ownerService.updateOwner(id, ownerDto);
        return ResponseEntity.ok(ownerMapper.entityToDto(updatedOwner));
    }

    @DeleteMapping("/{id}")
    public void deleteOwnerById(@PathVariable @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        ownerService.deleteOwnerById(id);
    }

}
