package com.example.myportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConfigResponse extends GenericResponse {
	
	private KeyValue account;
	private KeyValue customer;
	private String thumb;
	private Object profileInfo;

}
