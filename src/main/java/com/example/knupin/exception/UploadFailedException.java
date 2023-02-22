package com.example.knupin.exception;

public class UploadFailedException extends RuntimeException {
    public UploadFailedException(String msg) {
        super(msg);
    }

    public UploadFailedException() {
        super();
    }
}