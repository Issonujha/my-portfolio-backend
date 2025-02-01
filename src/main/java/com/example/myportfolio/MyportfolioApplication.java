package com.example.myportfolio;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MyportfolioApplication {

	public static void main(String[] args) {
		// Load the .env file
        Dotenv dotenv = Dotenv.load();

        // Set system environment variables
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("EMAIL", dotenv.get("EMAIL"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
        System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT"));
		SpringApplication.run(MyportfolioApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner testDataSource(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Connected to the database: " + connection.getCatalog());
            }
        };
    }

}
