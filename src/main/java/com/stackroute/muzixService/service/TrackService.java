package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExsitsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExsitsException;
    public List<Track> getAllTracks() throws TrackNotFoundException;
    public Track getTrackByTrackId(int id) throws TrackNotFoundException;
    public Track getTrackByName(String trackName) throws TrackNotFoundException;
    //public int updateComments(int id,String comments) throws TrackNotFoundException;
    public Track updateComments(int trackId,Track track) throws TrackNotFoundException;
    public String deleteTrack(int id) throws TrackNotFoundException;
}
