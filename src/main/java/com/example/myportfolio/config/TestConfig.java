package com.example.myportfolio.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;

public class TestConfig {

	@Bean
	public TemplateEngine templateEngine() {
		return new TemplateEngine();
	}

}
