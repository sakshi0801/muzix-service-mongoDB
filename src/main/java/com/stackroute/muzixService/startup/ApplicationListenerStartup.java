package com.stackroute.muzixService.startup;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.repository.TrackRepository;
import com.stackroute.muzixService.service.TrackService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerStartup implements ApplicationListener<ContextRefreshedEvent> {

    private TrackRepository trackRepository;

    public ApplicationListenerStartup(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track1=new Track(1,"aakh mare","simba track");

        trackRepository.save(track1);
    }
}
