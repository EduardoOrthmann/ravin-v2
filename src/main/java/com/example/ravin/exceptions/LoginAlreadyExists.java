package com.example.ravin.exceptions;

import com.example.ravin.utils.constants.ErrorMessages;

public class LoginAlreadyExists extends RuntimeException {
    public LoginAlreadyExists(String message) {
        super(message);
    }

    public LoginAlreadyExists() {
        super(ErrorMessages.LOGIN_ALREADY_EXISTS);
    }
}
