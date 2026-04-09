package edu.dosw.parcial.dto;

import java.util.List;

public record ComputadorResponse(
        Long id,
        String serial,
        String marca,
        Integer numeroLaboratorio,
        boolean ocupado,
        Long registradoPorId,
        List<PerifericoResponse> perifericos) {
}