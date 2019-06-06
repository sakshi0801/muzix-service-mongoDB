package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrackRepository extends JpaRepository<Track,Integer> {

    @Query("select t from Track t where t.trackName=:trackName")
    public Track getTrackByName(@Param("trackName") String trackName);

}
