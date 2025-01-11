package com.example.myportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.MailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

	public void sendMailToMeFromWeb(MailRequest mailRequest) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setFrom("sjha9563@gmail.com");
		helper.setTo(mailRequest.getTo());
		helper.setSubject(mailRequest.getSubject());
		helper.setText(mailRequest.getBody(), true);

		mailSender.send(mimeMessage);
		System.out.println("HTML email sent successfully to " + mailRequest.getTo());

	}

}
