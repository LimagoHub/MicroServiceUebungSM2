package de.ing.creditapplication.cityscoring.events.messaging;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import de.ing.creditapplication.cityscoring.events.CityScoringDoneEvent;
import de.ing.creditapplication.cityscoring.events.CreditApplicationEnteredEvent;
import de.ing.creditapplication.cityscoring.events.CreditApplicationsCityScoringChannels;
import de.ing.creditapplication.cityscoring.models.CreditApplication;

@Component
public class CreditApplicationEventListener {
	private final CreditApplicationsCityScoringChannels creditApplicationCityScoringChannels;

	public CreditApplicationEventListener(CreditApplicationsCityScoringChannels creditApplicationCityScoringChannels) {
		this.creditApplicationCityScoringChannels = creditApplicationCityScoringChannels;
	}
	
	@StreamListener(CreditApplicationsCityScoringChannels.CREDIT_APPLICATION_ENTERED)
	public void receiveCreditApplicationEnteredIn(@Payload CreditApplicationEnteredEvent creditApplicationEnteredEvent) {
		CreditApplication creditApplication = creditApplicationEnteredEvent.getCreditApplication();
		
		CityScoringDoneEvent result = CityScoringDoneEvent.builder().creditApplicationId(creditApplication.getCreditApplicationId()).build();
		Message<CityScoringDoneEvent> message = MessageBuilder.withPayload(result).build();
		
		
		if (creditApplication.scoringProcess()) {
			creditApplicationCityScoringChannels.cityCheckPositiveOut().send(message);
		} else {
			creditApplicationCityScoringChannels.cityCheckNegativeOut().send(message);
		}
	}
}
