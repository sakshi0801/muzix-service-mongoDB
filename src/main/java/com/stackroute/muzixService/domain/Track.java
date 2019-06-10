/**
 * This is a domain class Track
 */

package com.stackroute.muzixService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    private int trackId;

    private String trackName;

    private String comments;

}
