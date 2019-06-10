/**
 * This class provide implementation of methods declared in TrackService interface.
 */

package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@CacheConfig(cacheNames = "music")
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    @Value("${spring.trackNotFound}")
    private String trackNotFound;

    @Value("${spring.trackAlreadyExist}")
    private String trackAlreadyExist;

    public void simulateDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //This method is used to save a track
    @CacheEvict(allEntries = true)
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException(trackAlreadyExist);
        }

        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException(trackAlreadyExist);
        }
        return savedTrack;
    }

    //This method is used to get all tracks
    @Cacheable
    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException {
        simulateDelay();
        List<Track> list=(List<Track>)trackRepository.findAll();
        if(list.isEmpty()){
            throw new TrackNotFoundException(trackNotFound);
        }
        return list;
    }

    //This method is used to get track by its id
    @Override
    public Track getTrackByTrackId(int trackId) throws TrackNotFoundException {
        Track foundTrack=null;
        if(trackRepository.existsById(trackId)){
            Optional optional=trackRepository.findById(trackId);
            foundTrack=(Track) optional.get();
        }
        else {
            throw new TrackNotFoundException(trackNotFound);
        }
        return foundTrack;
    }

    //This method is used to update comments of a track
    @Override
    @CacheEvict(allEntries = true)
    public Track updateComments(Track track) throws TrackNotFoundException {
        Track track1=null;
        if(trackRepository.existsById(track.getTrackId())){
            Optional optional=trackRepository.findById(track.getTrackId());
            if(optional.isPresent()) {
                track1 = (Track) optional.get();
                track1.setComments(track.getComments());
                track1 = trackRepository.save(track1);
            }
            else {
                throw new TrackNotFoundException(trackNotFound);
            }
        }
        else {
            throw new TrackNotFoundException(trackNotFound);
        }
        return track1;
    }

    //This method is used to delete a track
    @Override
    @CacheEvict(allEntries = true)
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if(optional.isPresent()){
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException(trackNotFound);
        }
        return trackRepository.findAll();
    }
}
