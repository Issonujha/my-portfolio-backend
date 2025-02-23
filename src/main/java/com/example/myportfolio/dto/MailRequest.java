package com.example.myportfolio.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequest {
	
	private String body;
	private String subject;
	private String to;
	
	private String template;
	private Map<String, String> variables;

}
