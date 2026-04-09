package edu.dosw.parcial.mapper;

import edu.dosw.parcial.dto.ColaboradorRequest;
import edu.dosw.parcial.dto.ColaboradorResponse;
import edu.dosw.parcial.entity.ColaboradorEntity;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

    @Mapping(target = "rol", source = "rol.nombre")
    ColaboradorResponse toResponse(ColaboradorEntity entity);

    List<ColaboradorResponse> toResponseList(List<ColaboradorEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    ColaboradorEntity toEntity(ColaboradorRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    void updateEntity(ColaboradorRequest request, @MappingTarget ColaboradorEntity entity);
}