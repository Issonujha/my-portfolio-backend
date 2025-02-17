package com.example.myportfolio.dto;

import lombok.Data;

@Data
public class ImageDTO {
	
	private String id;
	private String awsUrl;

	private String description;

	private String fileName;
	
	private Long size;

}
