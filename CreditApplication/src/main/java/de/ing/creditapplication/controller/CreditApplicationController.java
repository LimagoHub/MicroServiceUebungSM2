package de.ing.creditapplication.controller;

import javax.validation.Valid;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.ing.creditapplication.events.CreditApplicationChannels;
import de.ing.creditapplication.events.CreditApplicationEnteredEvent;
import de.ing.creditapplication.models.CreditApplication;

@Controller
class CreditApplicationController {

	private final CreditApplicationChannels creditApplicationChannels;
	//private final CreditApplicationRepository repository;

	public CreditApplicationController(CreditApplicationChannels creditApplicationChannels) {
		this.creditApplicationChannels = creditApplicationChannels;
		//this.repository = repository;
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
		CreditApplicationEnteredEvent result = CreditApplicationEnteredEvent.builder().creditApplication(creditApplication).build();

//		repository.save(creditApplication);

		Message<CreditApplicationEnteredEvent> message = MessageBuilder.withPayload(result).build();
		creditApplicationChannels.creditApplicationOut().send(message);
//		model.addAttribute("creditApplications", creditApplication);

		return "StatusCreditApplication";
	}
}
