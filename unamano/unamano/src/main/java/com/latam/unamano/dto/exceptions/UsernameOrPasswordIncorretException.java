package com.latam.unamano.dto.exceptions;

public class UsernameOrPasswordIncorretException extends RuntimeException {
	public UsernameOrPasswordIncorretException(String message) {
		super(message);
	}
}
