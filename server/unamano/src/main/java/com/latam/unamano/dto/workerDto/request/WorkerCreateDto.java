package com.latam.unamano.dto.workerDto.request;

import com.latam.unamano.persistence.entities.user.User;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record WorkerCreateDto(

        User user,
        @NotEmpty
        List<Long> occupations
) {

}
