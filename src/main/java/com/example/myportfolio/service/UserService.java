package com.example.myportfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.myportfolio.dto.ConfigResponse;
import com.example.myportfolio.dto.KeyValue;
import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.entity.User;
import com.example.myportfolio.jwt.JwtAuthenticationHelper;
import com.example.myportfolio.mapper.UserMapper;
import com.example.myportfolio.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtAuthenticationHelper authenticationHelper;
	
	
	public List<Signup> getByCustomerId(String customerId) {
		return userRepository.findByCustomerId(customerId)
				.stream().map(u -> UserMapper.INSTANCE.toSignUp(u))
				.toList();
	}


	public void setConfigDetails(ConfigResponse configResponse, String authorization) {
		String username = authenticationHelper.getUsernameFromToken(authorization.substring(7));
		if (StringUtils.hasText(username)) {
			User loggedInUser = userRepository.findByUsername(username).orElse(null);
			if (loggedInUser != null) {
				configResponse.setAccount(new KeyValue(loggedInUser.getId(), loggedInUser.getName()));
				if (loggedInUser.getCustomer() != null)
					configResponse.setCustomer(
							new KeyValue(loggedInUser.getCustomer().getId(), loggedInUser.getCustomer().getName()));
				configResponse.setThumb(loggedInUser.getThumb());
			}
		}
	}
	
	

}
