package com.challenge.sendgrid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.service.EmailService;

@RestController
public class SendGridConfig {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/sendMail/{email}")
	public String sendEmail(@PathVariable(value = "email", required = true) String email) {
		
		return emailService.sendEmail(email);
	}

}
