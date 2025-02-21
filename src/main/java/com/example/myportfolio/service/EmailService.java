package com.example.myportfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.MailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value(value = "${spring.mail.username}")
	private String username;
	
	@Async
	public void sendMailToMeFromWeb(MailRequest mailRequest) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setFrom(username);
		helper.setTo(mailRequest.getTo());
		helper.setSubject(mailRequest.getSubject());
		helper.setText(mailRequest.getBody(), true);
		mailSender.send(mimeMessage);
		logger.info(String.format("HTML email sent successfully to %s", mailRequest.getTo()));
	}

}