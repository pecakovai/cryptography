package com.example.cryptography.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {

    public InvalidUsernameOrPasswordException() {
        super("The username box cannot be empty\n" +
                "Password is required");
    }
}
