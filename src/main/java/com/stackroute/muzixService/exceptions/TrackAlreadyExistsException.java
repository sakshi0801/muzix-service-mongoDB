/**
 * This is a custom defined exception that is thrown when a track is already present
 */

package com.stackroute.muzixService.exceptions;

public class TrackAlreadyExistsException extends Exception {

    private String message;

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
