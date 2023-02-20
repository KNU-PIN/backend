package com.example.knupin.exception;

public class PinNotFoundException extends RuntimeException {
    public PinNotFoundException(String msg) {
        super(msg);
    }

    public PinNotFoundException() {
        super();
    }
}