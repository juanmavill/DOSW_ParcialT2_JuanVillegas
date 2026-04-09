package edu.dosw.parcial.repository;

import edu.dosw.parcial.entity.PermissionEntity;
import edu.dosw.parcial.model.PermissionName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByNombre(PermissionName nombre);
}