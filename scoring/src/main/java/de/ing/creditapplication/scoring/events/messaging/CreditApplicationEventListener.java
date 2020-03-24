package de.ing.creditapplication.scoring.events.messaging;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import de.ing.creditapplication.scoring.events.CreditApplicationEnteredEvent;
import de.ing.creditapplication.scoring.events.CreditApplicationScoringChannels;
import de.ing.creditapplication.scoring.events.ScoringDoneEvent;
import de.ing.creditapplication.scoring.models.CreditApplication;

@Component
public class CreditApplicationEventListener {
	private final CreditApplicationScoringChannels creditApplicationScoringChannels;

	public CreditApplicationEventListener(CreditApplicationScoringChannels creditApplicationScoringChannels) {
		this.creditApplicationScoringChannels = creditApplicationScoringChannels;
	}
	
	
	@StreamListener(CreditApplicationScoringChannels.CREDIT_APPLICATION_ENTERED)
	public void receiveCreditApplicationEnteredIn(@Payload CreditApplicationEnteredEvent creditApplicationEnteredEvent) {
		CreditApplication creditApplication = creditApplicationEnteredEvent.getCreditApplication();
		
		
		
		ScoringDoneEvent result = ScoringDoneEvent.builder().creditApplicationId(creditApplication.getCreditApplicationId()).build();
		Message<ScoringDoneEvent> message = MessageBuilder.withPayload(result).build();
		
		
		if (creditApplication.scoringProcess()) {
			creditApplicationScoringChannels.scoringPositiveOut().send(message);
		} else {
			creditApplicationScoringChannels.scoringNegativeOut().send(message);
		}
	}
}
