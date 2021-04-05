package com.brabos.bahia.instagram.test.services.exceptions;

public class BadObjectFormation extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadObjectFormation(String message) {
        super(message);
    }

    public BadObjectFormation(String message, Throwable cause) {
        super(message, cause);
    }
}
