package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,Integer> {

    /*@Query("select t from Track t where t.trackName=:trackName")
    public Track getTrackByName(@Param("trackName") String trackName);*/

}
