package com.example.skiSlope;

import com.example.skiSlope.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SkiSlopeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SkiSlopeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
