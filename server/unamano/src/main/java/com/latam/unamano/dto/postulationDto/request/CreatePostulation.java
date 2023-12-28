package com.latam.unamano.dto.postulationDto.request;

import jakarta.validation.constraints.NotNull;

public record CreatePostulation(
        @NotNull
        Long worker_id,
        @NotNull
        Long task_id) {

}
