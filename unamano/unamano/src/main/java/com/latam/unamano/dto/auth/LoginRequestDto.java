package com.latam.unamano.dto.auth;

import com.latam.unamano.utils.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
		@NotBlank
		String username,
		@NotBlank
		String password,
		@NotNull
		Role role) {

}
