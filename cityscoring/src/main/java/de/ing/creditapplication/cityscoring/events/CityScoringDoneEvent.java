package de.ing.creditapplication.cityscoring.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CityScoringDoneEvent extends BaseEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542106097538215622L;
	private String creditApplicationId;
}
