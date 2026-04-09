package edu.dosw.parcial.service;

import edu.dosw.parcial.dto.ColaboradorRequest;
import edu.dosw.parcial.dto.ColaboradorResponse;
import edu.dosw.parcial.entity.ColaboradorEntity;
import edu.dosw.parcial.entity.RoleEntity;
import edu.dosw.parcial.exception.ResourceNotFoundException;
import edu.dosw.parcial.mapper.ColaboradorMapper;
import edu.dosw.parcial.model.PermissionName;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.repository.ColaboradorRepository;
import edu.dosw.parcial.repository.RoleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    private static final String EMAIL_DOMAIN = "@escuelaing.edu.co";

    private final ColaboradorRepository colaboradorRepository;
    private final RoleRepository roleRepository;
    private final AuthorizationService authorizationService;
    private final ColaboradorMapper colaboradorMapper;

    public ColaboradorService(
            ColaboradorRepository colaboradorRepository,
            RoleRepository roleRepository,
            AuthorizationService authorizationService,
            ColaboradorMapper colaboradorMapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.roleRepository = roleRepository;
        this.authorizationService = authorizationService;
        this.colaboradorMapper = colaboradorMapper;
    }

    public List<ColaboradorResponse> findAll(RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CONSULTAR_COLABORADORES);
        return colaboradorMapper.toResponseList(colaboradorRepository.findAll());
    }

    public ColaboradorResponse create(RoleName actorRole, ColaboradorRequest request) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COLABORADORES);
        validateInstitutionalEmail(request.correo());

        RoleEntity role = roleRepository.findByNombre(request.rol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado: " + request.rol()));

        ColaboradorEntity entity = colaboradorMapper.toEntity(request);
        entity.setRol(role);
        return colaboradorMapper.toResponse(colaboradorRepository.save(entity));
    }

    public ColaboradorResponse update(Long id, RoleName actorRole, ColaboradorRequest request) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COLABORADORES);
        validateInstitutionalEmail(request.correo());

        ColaboradorEntity entity = colaboradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado: " + id));

        RoleEntity role = roleRepository.findByNombre(request.rol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado: " + request.rol()));

        colaboradorMapper.updateEntity(request, entity);
        entity.setRol(role);
        return colaboradorMapper.toResponse(colaboradorRepository.save(entity));
    }

    public void delete(Long id, RoleName actorRole) {
        authorizationService.requirePermission(actorRole, PermissionName.CRUD_COLABORADORES);
        if (!colaboradorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Colaborador no encontrado: " + id);
        }
        colaboradorRepository.deleteById(id);
    }

    private void validateInstitutionalEmail(String correo) {
        if (correo == null || !correo.toLowerCase().endsWith(EMAIL_DOMAIN)) {
            throw new IllegalArgumentException("El correo debe tener dominio " + EMAIL_DOMAIN);
        }
    }
}