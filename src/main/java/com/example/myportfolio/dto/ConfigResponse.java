package com.example.myportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigResponse {
	
	private KeyValue account;
	private KeyValue customer;
	private String thumb;
	private Object profileInfo;

}
