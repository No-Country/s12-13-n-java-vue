package com.latam.unamano.controller.exceptions;


import com.latam.unamano.exceptions.RatingNotFoundException;
import com.latam.unamano.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.latam.unamano.exceptions.BadDataEntryException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.latam.unamano.dto.exceptions.ErrorResponse;
import com.latam.unamano.exceptions.IncorretRoleException;
import com.latam.unamano.exceptions.UsernameOrPasswordIncorretException;

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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> err404(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadDataEntryException.class)
    public ResponseEntity<String> badDataEntry(BadDataEntryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> badDataEntry(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("User", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(RatingNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Rating", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
