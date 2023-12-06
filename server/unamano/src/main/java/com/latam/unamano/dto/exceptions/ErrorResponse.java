package com.latam.unamano.dto.exceptions;

import org.springframework.validation.FieldError;

public record ErrorResponse(String field, String error) {
	
	public ErrorResponse(FieldError error) {
		this(error.getField(), error.getDefaultMessage());
	}

}
