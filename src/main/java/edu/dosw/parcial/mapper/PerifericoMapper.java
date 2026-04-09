package edu.dosw.parcial.mapper;

import edu.dosw.parcial.dto.PerifericoRequest;
import edu.dosw.parcial.dto.PerifericoResponse;
import edu.dosw.parcial.entity.PerifericoEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerifericoMapper {

    @Mapping(target = "registradoPorId", source = "registradoPor.id")
    @Mapping(target = "computadorId", source = "computador.id")
    PerifericoResponse toResponse(PerifericoEntity entity);

    List<PerifericoResponse> toResponseList(List<PerifericoEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registradoPor", ignore = true)
    @Mapping(target = "computador", ignore = true)
    PerifericoEntity toEntity(PerifericoRequest request);
}