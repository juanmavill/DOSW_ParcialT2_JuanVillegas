package edu.dosw.parcial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComputadorRequest(
        @NotBlank String serial,
        @NotBlank String marca,
        @NotNull Integer numeroLaboratorio,
        boolean ocupado,
        @NotNull Long registradoPorId) {
}