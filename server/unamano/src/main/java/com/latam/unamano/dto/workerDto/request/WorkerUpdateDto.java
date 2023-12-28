package com.latam.unamano.dto.workerDto.request;

import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WorkerUpdateDto(

       /* @NotNull
        Long id,
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @Email
        String email,
        @NotNull
        Address address,*/
        Long id,
        User user,
        @NotEmpty
        List<Long> occupations) {



}
