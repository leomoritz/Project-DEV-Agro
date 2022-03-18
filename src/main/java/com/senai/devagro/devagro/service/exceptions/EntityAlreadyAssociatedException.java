package com.senai.devagro.devagro.service.exceptions;

public class EntityAlreadyAssociatedException extends RuntimeException {

    public EntityAlreadyAssociatedException(String message) {
        super(message);
    }
}
