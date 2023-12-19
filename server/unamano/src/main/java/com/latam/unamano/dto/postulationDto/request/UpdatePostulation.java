package com.latam.unamano.dto.postulationDto.request;

import com.latam.unamano.utils.PostulationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePostulation(
        Long id,
        @NotNull
        Long worker_id,
        @NotBlank
        PostulationStatus status,
        @NotNull
        Long task_id) {
}
