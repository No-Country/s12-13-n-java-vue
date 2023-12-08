package com.latam.unamano.dto.postulationDto;

import jakarta.validation.constraints.NotNull;

public record UpdatePostulation(
        Long id,
        @NotNull
        Long worker_id,
        @NotNull
        Long task_id) {
}
