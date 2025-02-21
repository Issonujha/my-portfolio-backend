package com.example.myportfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.CustomerDTO;
import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.dto.Signup;
import com.example.myportfolio.entity.Customer;
import com.example.myportfolio.mapper.CustomerMapper;
import com.example.myportfolio.repository.CustomerRepository;

import jakarta.mail.MessagingException;

@Service
public class CustomerService {
	
	private Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	private AuthService authService;
	
	private CustomerRepository customerRepository;
	
	private EmailService emailService;
	
	public CustomerService(CustomerRepository customerRepository, AuthService authService, EmailService emailService) {
		this.customerRepository = customerRepository;
		this.authService = authService;
		this.emailService = emailService;
	}

	public CustomerDTO onBoard(CustomerDTO customerDto) {
		Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);
		if (customer != null && customer.getEmail().contains("@gmail.com")) {
			customer.setIden("CUST-" + customer.getEmail().substring(0, customer.getEmail().indexOf("@")));
			customer.setNameLowerCase(customerDto.getName().toLowerCase());
			customerRepository.save(customer);
			creatADefaultUserBasedOnCustomer(customer);
			return customerDto;
		}
		return null;
	}

	private void creatADefaultUserBasedOnCustomer(Customer customer) {
		Signup signup = Signup.builder().name(customer.getName()).email(customer.getEmail())
				.contact(customer.getContact()).address(customer.getAddress())
				.password(customer.getIden() + customer.getName().substring(0, 4)).role("ADMIN")
				.username(customer.getEmail()).build();
		authService.register(signup, customer);
		try {
			emailService.sendMailToMeFromWeb(MailRequest.builder().to(customer.getEmail())
					.subject("You're Onboarded successfully!!")
					.body("Hi " + customer.getName() + ", \n"
							+ " you have onborded and please verify and login to your account using the below credentials: \n username: "
							+ customer.getEmail() + " \n" + "Password: " + signup.getPassword() + "\n"
							+ "Thank you for choosing us!")
					.build());
		} catch (MessagingException e) {
			logger.info(customer.getEmail() + " Customer Onboarded but cannot send email." + customer.getId());
		}
	}

}
