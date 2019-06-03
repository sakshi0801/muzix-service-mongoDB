package com.stackroute.muzixService.controller;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExsitsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/muzix/")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExsitsException{
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity<String>("Track successfully added", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() throws TrackNotFoundException{
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackByTrackId(@PathVariable("id") int id) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        Track track=trackService.getTrackByTrackId(id);
        if(track==null){
            throw new TrackNotFoundException("Track not found");
        }
        else {
            responseEntity=new ResponseEntity<String>("Track found",HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("track2/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable("name") String name) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        Track track=trackService.getTrackByName(name);
        if(track==null){
            throw new TrackNotFoundException("Track not found");
        }
        else {
            responseEntity=new ResponseEntity<String>("Track found",HttpStatus.OK);
        }
        return responseEntity;
    }

    @PutMapping("track/update/{id}")
    public ResponseEntity<?> updateComments(@PathVariable("id") int id,@RequestBody Track track) throws TrackNotFoundException{
        ResponseEntity responseEntity;
        trackService.updateComments(id,track);
        responseEntity = new ResponseEntity<String>("Track updated", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("track/delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) throws TrackNotFoundException{
        return new ResponseEntity<String>(trackService.deleteTrack(id),HttpStatus.OK);
    }
}
