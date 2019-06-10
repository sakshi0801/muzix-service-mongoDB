/**
 * This class is used to provide seed data in the application using ApplicationListener interface
 */

package com.stackroute.muzixService.startup;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.repository.TrackRepository;
import com.stackroute.muzixService.service.TrackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationListenerStartup implements ApplicationListener<ContextRefreshedEvent> {

    private TrackRepository trackRepository;

    @Value("${spring.track.trackId1}")
    private int trackId;

    @Value("${spring.track.trackName1}")
    private String trackName;

    @Value("${spring.track.comments1}")
    private String comments;

    public ApplicationListenerStartup(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track1=new Track(trackId,trackName,comments);
        trackRepository.save(track1);
    }
}
