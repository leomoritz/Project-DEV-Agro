package com.senai.devagro.devagro.service.exceptions;

public class GenderEnumException extends RuntimeException {

    public GenderEnumException() {
        super("The reported value is unknown. Gender with value does not exists.");
    }

    public GenderEnumException(String message) {
        super(message);
    }
}
