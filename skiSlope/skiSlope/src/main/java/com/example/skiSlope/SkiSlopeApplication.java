package com.example.skiSlope;

import com.example.skiSlope.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkiSlopeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SkiSlopeApplication.class, args);
	}

	@Autowired
	private TicketRepository ticketRepository;
	@Override
	public void run(String... args) throws Exception {
//		Ticket ticket = new Ticket();
	}
}
