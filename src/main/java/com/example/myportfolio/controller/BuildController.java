package com.example.myportfolio.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.common.CommonConstants;
import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class BuildController {
	
	private Logger logger = LoggerFactory.getLogger(BuildController.class);
	
	@Autowired
	private EmailService emailService;
	
	@Value(value = "${spring.mail.username}")
	private String to;
	
	
	@GetMapping("/build")
	public void buildMail(@RequestParam String deployed) {
		MailRequest mailRequest = MailRequest
				.builder().subject("Build Success and Deployed!").to(
						to)
				.template("deployment")
				.variables(Map.of("URL", (deployed.equalsIgnoreCase("frontend") ? "https://portfolio.sonujha.in/"
						: "https://www.api.sonujha.in/"), "Deployed", deployed))
				.build();
		try {
			emailService.sendMailToMeFromWeb(mailRequest);
		} catch (MessagingException e) {
			logger.info(CommonConstants.SOMETHING_WENTS_WRONG);
		}
	}
	

}
