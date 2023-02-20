package com.example.knupin.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String msg) {
        super(msg);
    }

    public WrongPasswordException() {
        super();
    }
}