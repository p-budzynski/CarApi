package pl.kurs.mapper;

import org.mapstruct.*;
import pl.kurs.dto.EngineDto;
import pl.kurs.entity.Engine;

@Mapper(componentModel = "spring")
public interface EngineMapper {


    EngineDto entityToDto(Engine engine);

    @Mapping(target = "id", ignore = true)
    Engine dtoToEntity(EngineDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEngineFromDto(EngineDto dto, @MappingTarget Engine engine);
}
