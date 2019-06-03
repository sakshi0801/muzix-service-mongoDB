package com.stackroute.muzixService.controller;

import com.stackroute.muzixService.exceptions.TrackAlreadyExsitsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {
    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception){
        return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TrackAlreadyExsitsException.class)
    public ResponseEntity<Object> exception(TrackAlreadyExsitsException exception){
        return new ResponseEntity<>("Track already exists",HttpStatus.ALREADY_REPORTED);
    }
}
