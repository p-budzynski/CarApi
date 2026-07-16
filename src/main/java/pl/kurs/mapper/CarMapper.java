package pl.kurs.mapper;

import org.mapstruct.*;
import pl.kurs.dto.CarDto;
import pl.kurs.entity.Car;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "engine.id", target = "engineId")
    CarDto entityToDto(Car car);

    @Mapping(target = "id", ignore = true)
    Car dtoToEntity(CarDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromDto(CarDto dto, @MappingTarget Car car);

    Set<CarDto> entitiesToDtos(Set<Car> ownerCars);
}
