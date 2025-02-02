package com.example.myportfolio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequest {
	
	private String body;
	private String subject;
	private String to;

}
