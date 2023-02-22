package com.example.knupin.exception;

public class LikePinAlreadyExistException extends RuntimeException {
    public LikePinAlreadyExistException(String msg) {
        super(msg);
    }

    public LikePinAlreadyExistException() {
        super();
    }
}