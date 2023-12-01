package com.latam.unamano.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.latam.unamano.dto.exceptions.ErrorResponse;
import com.latam.unamano.dto.exceptions.UsernameOrPasswordIncorretException;
import com.latam.unamano.exceptions.IncorretRoleException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	var errors = ex.getFieldErrors().stream().map(ErrorResponse::new).toList();
    	return ResponseEntity.badRequest().body(errors);
    }
	
	@ExceptionHandler(IncorretRoleException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(IncorretRoleException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Role", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(UsernameOrPasswordIncorretException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(UsernameOrPasswordIncorretException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Username or password", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
