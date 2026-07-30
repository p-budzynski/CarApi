package pl.kurs.mapper;

import org.mapstruct.*;
import pl.kurs.dto.CarDto;
import pl.kurs.dto.CarRequestDto;
import pl.kurs.entity.Car;

import java.util.Set;

@Mapper(componentModel = "spring", uses = EngineMapper.class)
public interface CarMapper {

    @Mapping(source = "engine.id", target = "engineId")
    CarDto entityToDto(Car car);

    CarRequestDto entityToRequestDto(Car car);

    @Mapping(target = "id", ignore = true)
    Car dtoToEntity(CarDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateCarFromDto(CarDto dto, @MappingTarget Car car);

    Set<CarDto> entitiesToDtos(Set<Car> ownerCars);
}
