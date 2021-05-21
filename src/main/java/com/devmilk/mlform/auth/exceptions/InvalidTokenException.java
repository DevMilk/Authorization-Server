package com.devmilk.mlform.auth.exceptions;

public class InvalidTokenException extends Throwable {
    public InvalidTokenException(String message) {
        super(message);
    }
}