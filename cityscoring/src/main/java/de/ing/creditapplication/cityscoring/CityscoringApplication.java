package de.ing.creditapplication.cityscoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import de.ing.creditapplication.cityscoring.events.CreditApplicationsCityScoringChannels;

@SpringBootApplication
@EnableBinding(CreditApplicationsCityScoringChannels.class)
public class CityscoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityscoringApplication.class, args);
	}

}
