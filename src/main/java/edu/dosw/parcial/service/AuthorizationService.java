package edu.dosw.parcial.service;

import edu.dosw.parcial.entity.PermissionEntity;
import edu.dosw.parcial.entity.RoleEntity;
import edu.dosw.parcial.exception.ForbiddenOperationException;
import edu.dosw.parcial.model.PermissionName;
import edu.dosw.parcial.model.RoleName;
import edu.dosw.parcial.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final RoleRepository roleRepository;

    public AuthorizationService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void requirePermission(RoleName roleName, PermissionName permissionName) {
        RoleEntity role = roleRepository.findByNombre(roleName)
                .orElseThrow(() -> new ForbiddenOperationException("Rol no configurado en base de datos: " + roleName));

        boolean allowed = role.getPermisos()
                .stream()
                .map(PermissionEntity::getNombre)
                .anyMatch(permissionName::equals);

        if (!allowed) {
            throw new ForbiddenOperationException(
                    "El rol " + roleName + " no tiene permiso " + permissionName);
        }
    }
}