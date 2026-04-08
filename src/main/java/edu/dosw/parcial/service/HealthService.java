package edu.dosw.parcial.service;

import edu.dosw.parcial.model.ApiStatus;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public ApiStatus currentStatus() {
        return new ApiStatus("ok", "Proyecto base listo para el parcial");
    }
}
