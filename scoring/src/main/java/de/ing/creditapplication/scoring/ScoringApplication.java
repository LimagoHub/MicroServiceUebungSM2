package de.ing.creditapplication.scoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import de.ing.creditapplication.scoring.events.CreditApplicationScoringChannels;

@SpringBootApplication
@EnableBinding(CreditApplicationScoringChannels.class)
public class ScoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoringApplication.class, args);
	}

}
