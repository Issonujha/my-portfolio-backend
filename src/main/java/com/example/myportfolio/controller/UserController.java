package com.example.myportfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	

}
