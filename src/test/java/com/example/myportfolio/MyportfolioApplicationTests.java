package com.example.myportfolio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.myportfolio.service.EmailService;

@SpringBootTest
class MyportfolioApplicationTests {
	
	@Mock
    private TemplateEngine templateEngine;
	
	@InjectMocks
	private EmailService emailService;
	
	
	@Test
    public void testGetEmailContent() {
		
		when(templateEngine.process(anyString(), any(Context.class))).thenReturn("John Doe john.doe@example.com SecurePass123 https://example.com/login");
        // Given
        String customerName = "John Doe";
        String customerEmail = "john.doe@example.com";
        String password = "SecurePass123";
        String loginUrl = "https://example.com/login";
        String emailContent = emailService.getEmailContent("onboarding-template", Map.of("Name", customerName, "Email", customerEmail, "Password", password, "LoginUrl", loginUrl));

        // Then (Assertions)
        assertTrue(emailContent.contains(customerName));
        assertTrue(emailContent.contains(customerEmail));
        assertTrue(emailContent.contains(password));
        assertTrue(emailContent.contains(loginUrl));
    }

	

}
