package edu.dosw.parcial.repository;

import edu.dosw.parcial.entity.ColaboradorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
    Optional<ColaboradorEntity> findByCorreo(String correo);
}