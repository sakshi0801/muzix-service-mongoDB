package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExsitsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl2 implements TrackService {

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExsitsException {
        return null;
    }

    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track getTrackByTrackId(int id) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        return null;
    }

    @Override
    public Track updateComments(int trackId, Track track) throws TrackNotFoundException {
        return null;
    }

    @Override
    public String deleteTrack(int id) throws TrackNotFoundException {
        return null;
    }
}
