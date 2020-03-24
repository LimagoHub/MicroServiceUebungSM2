package de.ing.creditapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.ing.creditapplication.CreditApplicationRepository;
import de.ing.creditapplication.events.CreditApplicationChannels;
import de.ing.creditapplication.events.CreditApplicationEnteredEvent;
import de.ing.creditapplication.models.CreditApplication;

@Controller
class CreditApplicationController {

	private final CreditApplicationChannels creditApplicationChannels;
	private final CreditApplicationRepository repository;

	public CreditApplicationController(CreditApplicationChannels creditApplicationChannels,CreditApplicationRepository repository) {
		this.creditApplicationChannels = creditApplicationChannels;
		this.repository = repository;
	}
	
	@GetMapping("/")
	public String index() {
		return "index"; 
	}
	
	@GetMapping("/CreditApplicationCreator")
	public String creditApplicationCreator(Model model) {
		model.addAttribute("gruss", "Hallo Welt");
		model.addAttribute("creditApplication", new CreditApplication());
		return "CreditApplicationCreator";
	}
	
	@PostMapping("/SaveCreditApplication")
	public String saveCreditApplication(@Valid @ModelAttribute("creditApplication") CreditApplication creditApplication,	BindingResult errors, Model model) {
		if (errors.hasErrors()) {

			return "CreditApplicationCreator";
		}
		
		repository.save(creditApplication);
		
		
		CreditApplicationEnteredEvent result = CreditApplicationEnteredEvent.builder().creditApplication(creditApplication).build();

		

		Message<CreditApplicationEnteredEvent> message = MessageBuilder.withPayload(result).build();
		creditApplicationChannels.creditApplicationOut().send(message);
		
		
		model.addAttribute("creditApplication", creditApplication);

		return "StatusCreditApplication";
	}
	
	@GetMapping("/CreditApplicationOverview")
	public String creditApplicationOverview(Model model) {
		List<CreditApplication> creditApplications = repository.findAllAsList();

		model.addAttribute("creditApplications", creditApplications);

		return "CreditApplicationOverview";
	}

	@GetMapping("/StatusDetail/{id}")
	public String statusDetails(@PathVariable String id, Model model) {
		CreditApplication creditApplication = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found"));

		model.addAttribute("creditApplication", creditApplication);
		return "StatusCreditApplication";

	}

	@GetMapping("/StatusUpdate/{id}")
	public String statusUpdate(@PathVariable String id, Model model) {
		
		System.out.println("ID before .... " + id);
		CreditApplication creditApplication = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Not found"));
		
		System.out.println("ID after .... " + creditApplication.getCreditApplicationId());

		model.addAttribute("creditApplication", creditApplication);
		return "StatusCreditApplication";

	}
	
}
