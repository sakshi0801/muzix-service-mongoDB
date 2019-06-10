/**
 * This interface is used as a repository whose implementation is generated at runtime.
 * This interface extends MongoRepository interface that provide methods for CRUD operations.
 */

package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,Integer> {

}
