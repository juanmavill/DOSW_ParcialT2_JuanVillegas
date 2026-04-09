package edu.dosw.parcial.controller;

import edu.dosw.parcial.dto.ColaboradorRequest;
import edu.dosw.parcial.dto.ColaboradorResponse;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.service.ColaboradorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping
    public List<ColaboradorResponse> findAll(@RequestHeader("X-Rol") RoleName actorRole) {
        return colaboradorService.findAll(actorRole);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColaboradorResponse create(
            @RequestHeader("X-Rol") RoleName actorRole,
            @Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.create(actorRole, request);
    }

    @PutMapping("/{id}")
    public ColaboradorResponse update(
            @PathVariable Long id,
            @RequestHeader("X-Rol") RoleName actorRole,
            @Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.update(id, actorRole, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestHeader("X-Rol") RoleName actorRole) {
        colaboradorService.delete(id, actorRole);
    }
}