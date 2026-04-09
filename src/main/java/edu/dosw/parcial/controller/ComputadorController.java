package edu.dosw.parcial.controller;

import edu.dosw.parcial.dto.ComputadorRequest;
import edu.dosw.parcial.dto.ComputadorResponse;
import edu.dosw.parcial.dto.PerifericoRequest;
import edu.dosw.parcial.dto.PerifericoResponse;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.service.ComputadorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/computadores")
public class ComputadorController {

    private final ComputadorService computadorService;

    public ComputadorController(ComputadorService computadorService) {
        this.computadorService = computadorService;
    }

    @GetMapping
    public List<ComputadorResponse> findAll(@RequestHeader("X-Rol") RoleName actorRole) {
        return computadorService.findAll(actorRole);
    }

    @GetMapping("/{id}")
    public ComputadorResponse findById(@PathVariable Long id, @RequestHeader("X-Rol") RoleName actorRole) {
        return computadorService.findById(id, actorRole);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComputadorResponse create(
            @RequestHeader("X-Rol") RoleName actorRole,
            @Valid @RequestBody ComputadorRequest request) {
        return computadorService.create(actorRole, request);
    }

    @PutMapping("/{id}")
    public ComputadorResponse update(
            @PathVariable Long id,
            @RequestHeader("X-Rol") RoleName actorRole,
            @Valid @RequestBody ComputadorRequest request) {
        return computadorService.update(id, actorRole, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestHeader("X-Rol") RoleName actorRole) {
        computadorService.delete(id, actorRole);
    }

    @PostMapping("/{id}/perifericos")
    @ResponseStatus(HttpStatus.CREATED)
    public PerifericoResponse addPeripheral(
            @PathVariable Long id,
            @RequestHeader("X-Rol") RoleName actorRole,
            @Valid @RequestBody PerifericoRequest request) {
        return computadorService.addPeripheral(id, actorRole, request);
    }

    @GetMapping("/perifericos")
    public List<PerifericoResponse> findPeripherals(@RequestHeader("X-Rol") RoleName actorRole) {
        return computadorService.findPeripherals(actorRole);
    }
}