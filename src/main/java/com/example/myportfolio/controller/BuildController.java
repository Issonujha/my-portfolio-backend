package com.example.myportfolio.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.common.CommonConstants;
import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.railway.RailwayDeploymentDTO;
import com.example.myportfolio.service.EmailService;

@RestController
public class BuildController {

	private Logger logger = LoggerFactory.getLogger(BuildController.class);

	@Autowired
	private EmailService emailService;

	@Value(value = "${spring.mail.username}")
	private String to;

    @RequestMapping(value ="/build", method = RequestMethod.OPTIONS)
	public ResponseEntity<Object> buildMail(@RequestParam String deployed,
			@RequestBody RailwayDeploymentDTO railwayDeploymentData) {
		MailRequest mailRequest = MailRequest.builder().subject("Build Success and Deployed!").to(to)
				.template("deployment")
				.variables(Map.of("URL",
						(deployed.equalsIgnoreCase("frontend") ? "https://portfolio.sonujha.in/"
								: "https://api.sonujha.in/"),
						"Deployed", deployed, "User",
						(railwayDeploymentData.getDeployment() != null
								? String.valueOf(railwayDeploymentData.getDeployment().getValue())
								: "Sonu")))
				.body("Build Success").build();
		try {
			emailService.sendMailToMeFromWeb(mailRequest);
			logger.info(CommonConstants.SUCCESS);
			return ResponseEntity.ok(CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.info(CommonConstants.SOMETHING_WENTS_WRONG + " " + e.toString());
		}
		return ResponseEntity.notFound().build();
	}

}
