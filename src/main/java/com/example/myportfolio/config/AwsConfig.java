package com.example.myportfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
	
	@Bean
	public AmazonS3 s3Client() {
		return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2) // Use environment variable
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
	}

}
