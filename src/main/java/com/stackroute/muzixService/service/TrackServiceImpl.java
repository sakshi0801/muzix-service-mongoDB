package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
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

    public void simulateDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @CacheEvict(allEntries = true)
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("track already exists");
        }

        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("track already exists");
        }
        return savedTrack;
    }

    @Cacheable
    @Override
    public List<Track> getAllTracks() {
        simulateDelay();
        List<Track> list=(List<Track>)trackRepository.findAll();
        return list;
    }

    @Override
    public Track getTrackByTrackId(int trackId) throws TrackNotFoundException {
        Track foundTrack=null;
        if(trackRepository.existsById(trackId)){
            foundTrack=trackRepository.findById(trackId).get();
        }
        return foundTrack;
    }

/*
    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        Track foundTrack=null;
        foundTrack=trackRepository.getTrackByName(trackName);
        return foundTrack;
    }
*/

/*
    @Override
    @CacheEvict(allEntries = true)
    public Track updateComments(Track track) throws TrackNotFoundException {
        Track track1=trackRepository.getOne(track.getTrackId());
        if(trackRepository.existsById(track.getTrackId())){
            track1.setComments(track.getComments());
            track1=trackRepository.save(track1);
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        return track1;
    }
*/

    @Override
    @CacheEvict(allEntries = true)
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if(optional.isPresent()){
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        return trackRepository.findAll();
    }
}
