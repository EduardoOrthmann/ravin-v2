package com.example.ravin.exceptions;

import com.example.ravin.utils.constants.ErrorMessages;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String message) {
        super(message);
    }

    public CpfAlreadyExistsException() {
        super(ErrorMessages.CPF_ALREADY_EXISTS);
    }
}
