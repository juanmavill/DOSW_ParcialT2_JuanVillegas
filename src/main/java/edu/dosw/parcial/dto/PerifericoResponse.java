package edu.dosw.parcial.dto;

import edu.dosw.parcial.model.PeripheralType;

public record PerifericoResponse(
        Long id,
        PeripheralType tipo,
        String nombre,
        boolean inalambrico,
        Long registradoPorId,
        Long computadorId) {
}