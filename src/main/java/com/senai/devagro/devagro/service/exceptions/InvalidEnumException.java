package com.senai.devagro.devagro.service.exceptions;

public class InvalidEnumException extends RuntimeException {

    public InvalidEnumException(String message) {
        super("The reported value is unknown. ".concat(message));
    }

}
