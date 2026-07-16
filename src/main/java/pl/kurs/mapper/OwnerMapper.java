package pl.kurs.mapper;

import org.mapstruct.*;
import pl.kurs.dto.OwnerDto;
import pl.kurs.entity.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDto entityToDto(Owner owner);

    @Mapping(target = "id", ignore = true)
    Owner dtoToEntity(OwnerDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cars", ignore = true)
    void updateOwnerFromDto(OwnerDto dto, @MappingTarget Owner owner);

}
