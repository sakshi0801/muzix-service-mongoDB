package com.stackroute.muzixService.startup;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineAppStartupRunner  implements CommandLineRunner {

    private TrackRepository trackRepository;

    @Autowired
    private Environment env;

    public CommandLineAppStartupRunner(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        Track track=Track.builder().trackId(Integer.parseInt(env.getProperty("spring.track.trackId2")))
                .trackName(env.getProperty("spring.track.trackName2"))
                .comments(env.getProperty("spring.track.comments2"))
                .build();
        trackRepository.save(track);
    }
}
