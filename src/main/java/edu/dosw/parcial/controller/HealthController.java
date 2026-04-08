package edu.dosw.parcial.controller;

import edu.dosw.parcial.dto.HealthResponse;
import edu.dosw.parcial.mapper.HealthMapper;
import edu.dosw.parcial.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final HealthService healthService;
    private final HealthMapper healthMapper;

    public HealthController(HealthService healthService, HealthMapper healthMapper) {
        this.healthService = healthService;
        this.healthMapper = healthMapper;
    }

    @GetMapping
    public HealthResponse check() {
        return healthMapper.toResponse(healthService.currentStatus());
    }
}
