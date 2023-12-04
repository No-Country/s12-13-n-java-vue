package com.latam.unamano.dto.auth;

import com.latam.unamano.persistence.entities.user.User;
import com.latam.unamano.utils.Role;

public record UserResponseDto(String username, String email, String firstName, String LastName, String phoneNumber,
		/* ,Address address, */ String profilePicture,Role role) {

	public UserResponseDto(User user) {
		this(user.getUsername(), user.getEmail(), user.getFirstName(),user.getLastName(), user.getPhoneNumber(), user.getProfilePicture() ,user.getRole());
	}

}
