package com.example.knupin.advice;

import com.example.knupin.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PinBoardControllerAdvice {

    @ExceptionHandler(PinNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> pinNotFoundException(PinNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PinDeletedException.class)
    @ResponseBody
    public ResponseEntity<String> pinDeletedException(PinDeletedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GONE);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseBody
    public ResponseEntity<String> wrongPasswordException(WrongPasswordException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UploadFailedException.class)
    @ResponseBody
    public ResponseEntity<String> uploadFailedException(UploadFailedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LikePinAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<String> likePinAlreadyExistException(LikePinAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}