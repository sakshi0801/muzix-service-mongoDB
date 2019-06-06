package com.stackroute.muzixService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MuzixService {

	public static void main(String[] args) {
		SpringApplication.run(MuzixService.class, args);
	}

}
