package com.example.myportfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.mapper.UserMapper;
import com.example.myportfolio.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<Signup> getByCustomerId(String customerId) {
		return userRepository.findByCustomerId(customerId)
				.stream().map(u -> UserMapper.INSTANCE.toSignUp(u))
				.toList();
	}
	
	

}
