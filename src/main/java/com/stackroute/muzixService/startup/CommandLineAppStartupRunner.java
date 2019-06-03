package com.stackroute.muzixService.startup;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner  implements CommandLineRunner {

    private static final Logger logger= LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    private TrackRepository trackRepository;

    public CommandLineAppStartupRunner(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started with logger", Arrays.toString(args));
        Track track2=new Track(2,"apna time aayega","gully boy track");
        trackRepository.save(track2);
    }
}
