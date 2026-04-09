package edu.dosw.parcial.dto;

import edu.dosw.parcial.model.RoleName;
import java.time.LocalDate;

public record ColaboradorResponse(
        Long id,
        String correo,
        String nombre,
        String apellido,
        String telefono,
        LocalDate fechaNacimiento,
        RoleName rol) {
}