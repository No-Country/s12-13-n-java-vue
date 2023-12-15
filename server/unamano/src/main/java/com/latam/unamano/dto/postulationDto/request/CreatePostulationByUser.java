package com.latam.unamano.dto.postulationDto.request;

import jakarta.validation.constraints.NotNull;

public record CreatePostulationByUser(
        @NotNull
        Long user_id,
        @NotNull
        Long task_id) {

}
