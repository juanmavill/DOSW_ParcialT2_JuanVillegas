package edu.dosw.parcial.repository;

import edu.dosw.parcial.entity.RoleEntity;
import edu.dosw.parcial.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByNombre(RoleName nombre);
}