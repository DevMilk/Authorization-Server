package com.devmilk.mlform.auth.exceptions;

public class emailAlreadyExistsException extends Throwable {
    public emailAlreadyExistsException(String email_already_exists) {
        super(email_already_exists);
    }
}
