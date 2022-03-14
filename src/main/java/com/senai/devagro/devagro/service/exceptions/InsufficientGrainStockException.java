package com.senai.devagro.devagro.service.exceptions;

public class InsufficientGrainStockException extends RuntimeException {

    public InsufficientGrainStockException() {
        super("Grain stock is insufficient to decrease this quantity of grain");
    }

    public InsufficientGrainStockException(String message) {
        super(message);
    }
}
