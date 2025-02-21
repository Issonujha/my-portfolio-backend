package com.example.myportfolio.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CustomerDTO {
	
	@NonNull
	private String name;
	
	private String email;
	
	private String thumb;

}
