package com.example.myportfolio.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.JwtRequest;
import com.example.myportfolio.dto.JwtResponse;
import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.entity.Roles;
import com.example.myportfolio.entity.User;
import com.example.myportfolio.jwt.JwtAuthenticationHelper;
import com.example.myportfolio.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JwtAuthenticationHelper jwtHelper;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public JwtResponse login(JwtRequest jwtRequest) {
		
		//authenticate with Authentication manager
		this.doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		
		JwtResponse response = JwtResponse.builder().jwtToken(token).build();
		return response;
	}

	private void doAuthenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authenticationToken);

		}catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}

	public void register(Signup signup) {
		User user = new User();
        user.setName(signup.getName());
        user.setUsername(signup.getEmail());
        user.setPhone(signup.getContact());
        user.setAddress(signup.getAddress());
        user.setInfo(signup.getUsername());
        user.setRoles(Set.of(Roles.builder().roleName("CUSTOMER").build()));
        user.setPassword(passwordEncoder.encode(signup.getPassword()));
        userRepository.save(user);
	}
}
