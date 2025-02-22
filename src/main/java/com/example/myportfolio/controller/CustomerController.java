package com.example.myportfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.CustomerDTO;
import com.example.myportfolio.service.CustomerService;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("onboard")
	public ResponseEntity<Object> onBoardCustomer(@RequestBody CustomerDTO customerDto) {
		StringBuilder error = new StringBuilder();
		if (StringUtils.hasLength(customerDto.getName()) && StringUtils.hasLength(customerDto.getEmail())) {
			CustomerDTO customerDTO = customerService.onBoard(customerDto, error);
			if (customerDTO != null)
				return ResponseEntity.ok(customerDTO);
		}
		return ResponseEntity.badRequest().body(error.toString());
	}

}
