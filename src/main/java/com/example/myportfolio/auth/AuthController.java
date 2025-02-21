package com.example.myportfolio.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.JwtRequest;
import com.example.myportfolio.dto.JwtResponse;
import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
		return new ResponseEntity<JwtResponse>(authService.login(jwtRequest), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public String registerUser(@RequestBody Signup signup) {
		authService.register(signup, null);
		return "User Registered Successfully";
	}

}
