/**
 * This program is used for track controller
 */

package com.stackroute.muzixService.controller;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "muzixControllerApi",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/api/v1/")
public class TrackController {

    private TrackService trackService;

    @Value("${spring.trackNotFound}")
    private String trackNotFound;

    @Autowired
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }

    //This method is used to save a track
    @PostMapping("track")
    @ApiOperation("Save track")
    @ApiResponses(value = {@ApiResponse(code = 201,message = "CREATED",response = Track.class)})
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity<String>("Track successfully added", HttpStatus.CREATED);
        return responseEntity;
    }

    //This method is used to get all track
    @GetMapping("tracks")
    @ApiOperation("Get all tracks")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Track.class)})
    public ResponseEntity<?> getAllTracks() throws TrackNotFoundException{
        ResponseEntity responseEntity;
        if(trackService.getAllTracks().isEmpty()){
            throw new TrackNotFoundException(trackNotFound);
        }
        else {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        }
        return responseEntity;
    }

    //This method is used to find a track by its Id
    @GetMapping("track/{id}")
    @ApiOperation("Get all tracks with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Track.class)})
    public ResponseEntity<?> getTrackByTrackId(@PathVariable("id") int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        Track track=trackService.getTrackByTrackId(id);
        if(track==null){
            throw new TrackNotFoundException(trackNotFound);
        }
        else {
            responseEntity=new ResponseEntity<String>("Track found",HttpStatus.OK);
        }
        return responseEntity;
    }

    //This method is used to update comments of a track
    @PutMapping("track")
    @ApiOperation("update track comments")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Track.class)})
    public ResponseEntity<?> updateComments(@RequestBody Track track) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        trackService.updateComments(track);
        responseEntity = new ResponseEntity<String>("Track updated", HttpStatus.OK);
        return responseEntity;
    }

    //This method is used to delete a track by its Id
    @DeleteMapping("track/{id}")
    @ApiOperation("delete track with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Track.class)})
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) throws TrackNotFoundException{
        return new ResponseEntity<List<Track>>(trackService.deleteTrack(id),HttpStatus.OK);
    }
}