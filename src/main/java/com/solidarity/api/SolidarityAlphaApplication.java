package com.solidarity.api;

import com.solidarity.api.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolidarityAlphaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SolidarityAlphaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
