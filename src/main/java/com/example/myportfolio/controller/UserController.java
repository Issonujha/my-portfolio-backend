package com.example.myportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.ConfigResponse;
import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	public List<Signup> getAllUserByCustomer(@RequestParam String customerId) {
		return userService.getByCustomerId(customerId);
	}
	
	@GetMapping("/config")
	public ConfigResponse config(@RequestHeader("Authorization") String authorization) {
		ConfigResponse configResponse = new ConfigResponse();
		if (StringUtils.hasText(authorization) && authorization.length() > 8) {
			userService.setConfigDetails(configResponse, authorization);
		}
		return configResponse;
	}
	
	

}
