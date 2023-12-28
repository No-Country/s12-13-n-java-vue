package com.latam.unamano.exceptions;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(String message) {
        super(message);
    }
}
