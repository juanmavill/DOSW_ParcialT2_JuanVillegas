package edu.dosw.parcial.mapper;

import edu.dosw.parcial.dto.ComputadorRequest;
import edu.dosw.parcial.dto.ComputadorResponse;
import edu.dosw.parcial.entity.ComputadorEntity;
import java.util.List;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = PerifericoMapper.class)
public interface ComputadorMapper {

    @Mapping(target = "registradoPorId", source = "registradoPor.id")
    ComputadorResponse toResponse(ComputadorEntity entity);

    List<ComputadorResponse> toResponseList(List<ComputadorEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registradoPor", ignore = true)
    @Mapping(target = "perifericos", ignore = true)
    ComputadorEntity toEntity(ComputadorRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registradoPor", ignore = true)
    @Mapping(target = "perifericos", ignore = true)
    void updateEntity(ComputadorRequest request, @MappingTarget ComputadorEntity entity);
}