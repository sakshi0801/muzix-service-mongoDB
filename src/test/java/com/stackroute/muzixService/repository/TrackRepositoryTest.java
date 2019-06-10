/**
 * This class is used to test methods of TrackRepository
 */

package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    private Track track;

    @Before
    public void setup(){
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("duniya");
        track.setComments("lukka chuppi");
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track foundTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,foundTrack.getTrackId());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(2,"sorry","abcd");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTracks(){
        Track track1=new Track(3,"Tum hi ho","aashiqui 2");
        Track track2=new Track(4,"Ban ja rani","sulu");

        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list=trackRepository.findAll();
        Assert.assertEquals(3,list.get(0).getTrackId());
    }

    @Test
    public void testGetAllTracksFailure(){
        Track track1=new Track(3,"Tum hi ho","aashiqui 2");
        Track track2=new Track(4,"Ban ja rani","sulu");

        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list=trackRepository.findAll();
        Assert.assertNotEquals(4,list.get(0).getTrackId());
    }

    @Test
    public void testGetTrackById(){
        trackRepository.save(track);

        Track foundTrack=(Track) trackRepository.findById(1).get();
        Assert.assertEquals(track,foundTrack);
    }

    @Test
    public void testGetTrackByIdFailure(){
        trackRepository.save(track);

        Track foundTrack=trackRepository.findById(1).get();
        Assert.assertNotEquals(2,foundTrack.getTrackId());
    }

    @Test
    public void testDeleteTrack(){
        trackRepository.delete(track);
        List<Track> list=trackRepository.findAll();

        Assert.assertEquals(false,list.contains(track));
    }

    @Test
    public void testDeleteTrackFailure(){
        trackRepository.delete(track);
        List<Track> list=trackRepository.findAll();

        Assert.assertNotEquals(true,list.contains(track));
    }
}
