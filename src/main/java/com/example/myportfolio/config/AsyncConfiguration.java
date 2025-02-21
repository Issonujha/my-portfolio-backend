package com.example.myportfolio.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfiguration {
	
	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(3);
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setQueueCapacity(5);
		taskExecutor.setThreadNamePrefix("emailThreadStart-");
		taskExecutor.initialize();
		return taskExecutor;
	}

}
