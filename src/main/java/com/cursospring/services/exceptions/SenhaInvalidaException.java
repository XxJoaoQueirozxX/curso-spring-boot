package com.cursospring.services.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String message) {
        super(message);
    }
}
