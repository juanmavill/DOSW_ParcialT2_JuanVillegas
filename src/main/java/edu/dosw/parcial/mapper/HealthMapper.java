package edu.dosw.parcial.mapper;

import edu.dosw.parcial.dto.HealthResponse;
import edu.dosw.parcial.model.ApiStatus;
import org.springframework.stereotype.Component;

@Component
public class HealthMapper {

    public HealthResponse toResponse(ApiStatus apiStatus) {
        return new HealthResponse(apiStatus.status(), apiStatus.message());
    }
}
