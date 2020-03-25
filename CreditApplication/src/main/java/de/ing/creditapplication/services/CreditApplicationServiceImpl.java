package de.ing.creditapplication.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.ing.creditapplication.CreditApplicationRepository;
import de.ing.creditapplication.models.CreditApplication;

@Service
@Transactional
public class CreditApplicationServiceImpl implements CreditApplicationService {
	final private CreditApplicationRepository repository;

	public CreditApplicationServiceImpl(final CreditApplicationRepository repository) {
		this.repository = repository;
	}

	@Override
	public void processScoringNegative(String id) {
		CreditApplication creditApplication = retrieveCreditApplication(id); 
		
		creditApplication.setScoringState("negative");
		// TODO Event feuern
		creditApplication.setApplicationState("abgelehnt");
	}

	@Override
	public void processScoringPositive(String id) {
		CreditApplication creditApplication = retrieveCreditApplication(id);

		creditApplication.setScoringState("ok");
		if("ok".equalsIgnoreCase(creditApplication.getCityCheckState())) {
			// TODO Event feuern
			creditApplication.setApplicationState("genehmigt");
		}
	}

	@Override
	public void processCityScoringNegative(String id) {
		CreditApplication creditApplication = retrieveCreditApplication(id); 
		
		creditApplication.setCityCheckState("negative");
		// TODO Event feuern
		creditApplication.setApplicationState("abgelehnt");
	}

	@Override
	public void processCityScoringPositive(String id) {
		CreditApplication creditApplication = retrieveCreditApplication(id);

		creditApplication.setCityCheckState("ok");
		if("ok".equalsIgnoreCase(creditApplication.getScoringState())) {
			// TODO Event feuern
			creditApplication.setApplicationState("genehmigt");
		}
	}

	private CreditApplication retrieveCreditApplication(String id) {
		CreditApplication creditApplication = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found."));
		return creditApplication;
	}
}
