package de.ing.creditapplication.cityscoring.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CreditApplicationsCityScoringChannels {
	String CREDIT_APPLICATION_ENTERED = "creditApplicationEnteredIn";
	String CREDIT_APPLICATION_SCORING_POSITIVE = "cityCheckPositiveOut";
	String CREDIT_APPLICATION_SCORING_NEGATIVE = "cityCheckNegativeOut";

	@Output
	MessageChannel cityCheckNegativeOut();

	@Output
	MessageChannel cityCheckPositiveOut();

	@Input
	SubscribableChannel creditApplicationEnteredIn();
}