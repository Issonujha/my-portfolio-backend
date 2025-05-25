package com.example.myportfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.service.EmailService;

@RestController
@RequestMapping("/mail")
public class ProfileMailSender {
	
	@Autowired
    private EmailService emailService;
	
	Logger logger = LoggerFactory.getLogger(ProfileMailSender.class);
	
	@PostMapping("/send")
	public String sendMail(@RequestBody MailRequest mailRequest) {
		try {
			mailRequest.setBody(mailRequest.getTo() + "<br />" + mailRequest.getBody());
			mailRequest.setTo(null);
			logger.debug(mailRequest.getTo() + " " + mailRequest.getSubject() + " " + mailRequest.getBody());
			emailService.sendMailToMeFromWeb(mailRequest);
			return "Mail Send Successfully.";
		} catch (Exception e) {
			return "Something wrong happens : " + e.getMessage();
		}
	}

}
