package com.example.myportfolio.dto;

import lombok.Data;

@Data
public class MailRequest {
	
	private String body;
	private String subject;
	private String to;

}
