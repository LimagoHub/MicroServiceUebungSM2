package de.ing.creditapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import de.ing.creditapplication.events.CreditApplicationChannels;

// https://github.com/LimagoHub/MicroServiceUebungSM2

@SpringBootApplication
@EnableBinding(CreditApplicationChannels.class)
public class CreditApplicationDoof {

	public static void main(String[] args) {
		SpringApplication.run(CreditApplicationDoof.class, args);
	}

}
