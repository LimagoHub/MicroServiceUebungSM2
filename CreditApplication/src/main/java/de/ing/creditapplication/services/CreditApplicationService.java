package de.ing.creditapplication.services;

public interface CreditApplicationService {
	public void processScoringNegative(String id);
	public void processScoringPositive(String id);
	public void processCityScoringNegative(String id);
	public void processCityScoringPositive(String id);
}
