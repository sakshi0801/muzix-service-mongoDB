package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExsitsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExsitsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExsitsException("track already exists");
        }

        Track savedTrack=trackRepository.save(track);

        if(savedTrack==null){
            throw new TrackAlreadyExsitsException("track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException {
        if(trackRepository.findAll()==null){
            throw new TrackNotFoundException("track not found");
        }
        Iterable<Track> tracklist=trackRepository.findAll();
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackByTrackId(int trackId) throws TrackNotFoundException {
        Track foundTrack=null;
        if(trackRepository.existsById(trackId)){
            foundTrack=trackRepository.getOne(trackId);
        }
        if(foundTrack==null){
            throw new TrackNotFoundException("track not found");
        }
        return foundTrack;
    }

    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        Track foundTrack=null;
        foundTrack=trackRepository.getTrackByName(trackName);
        if(foundTrack==null){
            throw new TrackNotFoundException("track not found");
        }
        return foundTrack;
    }

    @Override
    public Track updateComments(int trackId, Track track) throws TrackNotFoundException {
        if(trackRepository.existsById(trackId)){
            track.setComments(track.getComments());
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        Track track1=trackRepository.save(track);
        return track1;
    }

    @Override
    public String deleteTrack(int id) throws TrackNotFoundException {
        if(trackRepository.existsById(id)){
            trackRepository.deleteById(id);
        }
        else {
            throw new TrackNotFoundException("track not found");
        }
        return "track deleted";
    }
}
