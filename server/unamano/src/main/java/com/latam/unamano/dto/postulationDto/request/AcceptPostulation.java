package com.latam.unamano.dto.postulationDto.request;

import jakarta.validation.constraints.NotNull;

public record AcceptPostulation(
        @NotNull
        Long postulation_id,
        @NotNull
        Long task_id) {

}
