package com.devmilk.authserver.auth.exceptions;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String message) {
        super(message);
    }
}