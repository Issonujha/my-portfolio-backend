package com.example.myportfolio.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.myportfolio.dto.MailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private Logger logger = LoggerFactory.getLogger(EmailService.class);

	private final JavaMailSender mailSender;

	@Value(value = "${spring.mail.username}")
	private String username;
	
    private final TemplateEngine templateEngine;
	
	public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	/**
	 * For fetching data of template
	 * 
	 * @param template - This template should be in side "src/main/resources/templates/"
	 * @param variables
	 * @return
	 */
	public String getEmailContent(String template, Map<String, String> variables) {
		Context context = new Context();
		if (!ObjectUtils.isEmpty(variables)) {
			variables.entrySet().forEach(var -> {
				context.setVariable(var.getKey(), var.getValue());
			});
		}
		logger.info("template is " + template);
		return templateEngine.process(template, context);
	}
	
	@Async
	public void sendMailToMeFromWeb(MailRequest mailRequest) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setFrom(username);
		if (mailRequest.getTo() == null) {
			mailRequest.setTo(username);
		}
		helper.setTo(mailRequest.getTo());
		helper.setSubject(mailRequest.getSubject());
		if (!ObjectUtils.isEmpty(mailRequest.getVariables()) && !ObjectUtils.isEmpty(mailRequest.getTemplate())) {
			String text = getEmailContent(mailRequest.getTemplate(), mailRequest.getVariables());
			logger.info(text);
			helper.setText(StringUtils.hasText(text) ? text : mailRequest.getBody(), true);
		} else
			helper.setText(mailRequest.getBody(), true);
		mailSender.send(mimeMessage);
		logger.info(String.format("HTML email sent successfully to %s", mailRequest.getTo()));
	}

}