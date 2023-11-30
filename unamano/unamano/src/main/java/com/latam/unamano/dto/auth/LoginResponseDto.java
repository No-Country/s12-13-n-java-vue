package com.latam.unamano.dto.auth;

import com.latam.unamano.persistence.entities.Login;
import com.latam.unamano.utils.Role;

public record LoginResponseDto(String username, Role role) {

	public LoginResponseDto(Login user) {
		this(user.getUsername(), user.getRole());
	}

}
