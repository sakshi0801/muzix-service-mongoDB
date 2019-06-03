package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    @Query("select t from Track t where t.trackName=:trackName")
    public Track getTrackByName(@Param("trackName") String trackName);

    /*@Transactional
    @Modifying
    @Query("update Track t set t.comments=:comments where t.trackId=:trackId")
    public int updateComments(@Param("trackId") int trackId,@Param("comments") String comments);*/
}
