package edu.dosw.parcial.mapper;

import edu.dosw.parcial.dto.HealthResponse;
import edu.dosw.parcial.model.ApiStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthMapper {

    HealthResponse toResponse(ApiStatus apiStatus);
}
