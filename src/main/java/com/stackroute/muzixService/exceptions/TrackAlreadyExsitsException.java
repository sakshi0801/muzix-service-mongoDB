package com.stackroute.muzixService.exceptions;

public class TrackAlreadyExsitsException extends Exception {

    private String message;

    public TrackAlreadyExsitsException() {
    }

    public TrackAlreadyExsitsException(String message) {
        super(message);
        this.message = message;
    }
}
