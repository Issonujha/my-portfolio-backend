package com.example.myportfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.myportfolio.entity.User;
import com.example.myportfolio.jwt.JwtAuthenticationFilter;
import com.example.myportfolio.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class PortfolioConfig {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF for APIs (or configure it if needed)
				.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())

				// Configure endpoint authorization
				.authorizeHttpRequests(authz -> authz
						// Public endpoints that do not require authentication
						.requestMatchers("/", "/build**", "/logs", "/auth/**", "/projects/all", "/get/{id}", "/mail/send",
								"/customer/onboard")
						.permitAll()

						// Protected endpoints that require authentication
						.anyRequest().authenticated() // Other endpoints require authentication
				)

				// Configure stateless session management (important for REST APIs)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Add authentication filter (JWT or similar token-based authentication)
				// Apply the filter only to the secured routes
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
		return userRequest -> {
			OidcUserService service = new OidcUserService();
			OidcUser oidcUser = service.loadUser(userRequest);
			User user = userRepository.findByUsername(oidcUser.getEmail())
					.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
			return new DefaultOidcUser(user.getAuthorities(), oidcUser.getIdToken());
		};
	}

}
