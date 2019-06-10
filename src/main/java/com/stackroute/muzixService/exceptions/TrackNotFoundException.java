/**
 * This is a custom defined exception that is thrown when a track is not found
 */

package com.stackroute.muzixService.exceptions;

public class TrackNotFoundException extends Exception {

    private String message;

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
