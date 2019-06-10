/**
 * This class is used to test methods of TrackService
 */

package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> tracks=null;

    @Autowired
    private Environment environment;
    private Optional optional;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);//initializes mock object
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("duniya");
        track.setComments("lukka chuppi");

        tracks=new ArrayList<>();
        tracks.add(track);

        optional=Optional.of(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedUser" + savedTrack);
        Assert.assertEquals(track, savedTrack);

        /*doThrow(new TrackAlreadyExistsException("track already exists")).when(trackRepository).findById(eq(1));
        trackService.saveTrack(track);*/
    }

    @Test
    public void getAllTracksTestSuccess() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(tracks);
        List<Track> list=trackService.getAllTracks();
        Assert.assertEquals(tracks,list);
    }

    @Test(expected = TrackNotFoundException.class)
    public void getAllTracksTestfailure() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(new ArrayList<Track>(){});
        List<Track> list=trackService.getAllTracks();
        Assert.assertNotEquals(tracks,list);
    }

    @Test
    public void getTrackByIdtest() throws TrackNotFoundException {
        when(trackRepository.save(track)).thenReturn(track);
        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        when(trackRepository.findById(track.getTrackId())).thenReturn(optional);
        Track track1=(Track) optional.get();
        Track getTrack=trackService.getTrackByTrackId(track.getTrackId());
        Assert.assertEquals(track1,getTrack);
    }

    @Test
    public void deleteTrackTest() throws TrackNotFoundException {
        when(trackRepository.findById(1)).thenReturn(optional);
        List<Track> actualList=trackService.deleteTrack(track.getTrackId());
        Assert.assertEquals(false,actualList.contains(track));
    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteTrackTestFailure() throws TrackNotFoundException {
        when(trackRepository.findById(10)).thenReturn(optional);
        List<Track> actualList=trackService.deleteTrack(track.getTrackId());
        Assert.assertNotEquals(true,actualList.contains(track));
    }

    @Test
    public void updateCommentsTest() throws TrackNotFoundException {
        when(trackRepository.existsById(1)).thenReturn(true);
        when(trackRepository.findById(1)).thenReturn(optional);
        when(trackRepository.save(track)).thenReturn(track);
        Track updateTrack=new Track(1,"duniya","abcd");
        Track actualTrack=trackService.updateComments(updateTrack);
        Assert.assertEquals(updateTrack,actualTrack);
    }

    @Test(expected = TrackNotFoundException.class)
    public void updateCommentsTestFailure() throws TrackNotFoundException {
        when(trackRepository.save(track)).thenReturn(track);
        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        when(trackRepository.findById(track.getTrackId())).thenReturn(optional);
        Track updateTrack=new Track(10,"duniya dari","abcd");
        track.setComments(updateTrack.getComments());
        Track actualTrack=trackService.updateComments(updateTrack);
        Assert.assertNotEquals(updateTrack,actualTrack);
    }
}
