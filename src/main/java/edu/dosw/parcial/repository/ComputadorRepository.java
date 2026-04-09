package edu.dosw.parcial.repository;

import edu.dosw.parcial.entity.ComputadorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputadorRepository extends JpaRepository<ComputadorEntity, Long> {
    Optional<ComputadorEntity> findBySerial(String serial);
}