package com.stackroute.muzixService.exceptions;

import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception){
        return new ResponseEntity<>("Track not found", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> exception(TrackAlreadyExistsException exception){
        return new ResponseEntity<>("Track already exists",HttpStatus.CONFLICT);
    }
}
