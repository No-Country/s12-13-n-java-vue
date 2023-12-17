package com.latam.unamano.exceptions;

public class OperationDeniedException extends RuntimeException {
    public OperationDeniedException(String s) {
        super(s);
    }
}
