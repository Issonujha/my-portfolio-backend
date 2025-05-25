package com.example.myportfolio.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myportfolio.common.CommonConstants;
import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.railway.DeploymentDTO;
import com.example.myportfolio.service.EmailService;

@Component
@Order(0)
public class BuildController implements ApplicationListener<ApplicationReadyEvent> {

	private Logger logger = LoggerFactory.getLogger(BuildController.class);

	@Autowired
	private EmailService emailService;

	@Value(value = "${spring.mail.username}")
	private String to;

	@Async
	public ResponseEntity<String> buildMail(@RequestParam String deployed,
			@RequestBody DeploymentDTO railwayDeploymentData) {
		MailRequest mailRequest = MailRequest.builder().subject("Build Success and Deployed!").to(to)
				.template("email-templates/deployment")
				.variables(Map.of("URL",
						(deployed.equalsIgnoreCase("frontend") ? "https://portfolio.sonujha.in/"
								: "https://api.sonujha.in/"),
						"Deployed", deployed, "User",
						(railwayDeploymentData.getDeployment() != null
								? String.valueOf(railwayDeploymentData.getDeployment())
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

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ResponseEntity<String> response = buildMail("backend",
				DeploymentDTO.builder().deployment("Sonu Jha").build());
		logger.info(response.getBody());
	}

}
