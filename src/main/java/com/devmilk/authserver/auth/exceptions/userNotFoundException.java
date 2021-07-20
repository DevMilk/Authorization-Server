package com.devmilk.authserver.auth.exceptions;

public class userNotFoundException extends Throwable {
    public userNotFoundException(String user_not_found) {
        super(user_not_found);
    }
}
