/**
 * This class is used as a controller advice to handle global exception
 */

package com.stackroute.muzixService.exceptions;

import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {

    @Value("${spring.trackNotFound}")
    private String trackNotFound;

    @Value("${spring.trackAlreadyExist}")
    private String trackAlreadyExist;

    //This method handles TrackNotFoundException
    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception(TrackNotFoundException exception){
        return new ResponseEntity<>(trackNotFound, HttpStatus.NOT_FOUND);
    }

    //This method handles TrackAlreadyExistsException
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> exception(TrackAlreadyExistsException exception){
        return new ResponseEntity<>(trackAlreadyExist,HttpStatus.ALREADY_REPORTED);
    }
}
