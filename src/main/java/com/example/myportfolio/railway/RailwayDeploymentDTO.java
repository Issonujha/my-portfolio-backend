package com.example.myportfolio.railway;

import java.util.Map;

import lombok.Data;

@Data
public class RailwayDeploymentDTO {
	
	private Map.Entry<String, Map<String, Object>> deployment;
	private Map<String, Object> environment;
	private Map<String, Object> project;
	private String timestamp;
	private String type;
	

}
