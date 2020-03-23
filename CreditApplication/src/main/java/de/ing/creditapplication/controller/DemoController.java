package de.ing.creditapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/demo")
	public String demo(Model model) {
		
		model.addAttribute("message", "Dieser Text sollte erscheinen!");
		return "demo";
	}

}
