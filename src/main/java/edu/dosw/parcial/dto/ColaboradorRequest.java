package edu.dosw.parcial.dto;

import edu.dosw.parcial.model.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ColaboradorRequest(
        @NotBlank @Email String correo,
        @NotBlank String contrasena,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String telefono,
        @NotNull LocalDate fechaNacimiento,
        @NotNull RoleName rol) {
}