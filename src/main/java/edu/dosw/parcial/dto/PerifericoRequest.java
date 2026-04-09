package edu.dosw.parcial.dto;

import edu.dosw.parcial.model.PeripheralType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerifericoRequest(
        @NotNull PeripheralType tipo,
        @NotBlank String nombre,
        boolean inalambrico,
        @NotNull Long registradoPorId) {
}