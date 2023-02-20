package com.example.knupin.exception;

public class PinDeletedException extends RuntimeException {
    public PinDeletedException(String msg) {
        super(msg);
    }

    public PinDeletedException() {
        super();
    }
}