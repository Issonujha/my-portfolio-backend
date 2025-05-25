package com.example.myportfolio.railway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeploymentDTO {
	
	private Object deployment;
	private Object environment;
	private Object project;
	private String timestamp;
	private String type;
	

}
