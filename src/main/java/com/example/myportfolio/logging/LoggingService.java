package com.example.myportfolio.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.myportfolio.entity.User;

public class LoggingService {
	
	private static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

	public static void log(Logger logger1, User user, Exception exception) {
		String message = exception.getMessage();
		if (logger1 == null)
			logger1 = logger;
		logger1.info(String.format("{0}! {1}", message, user != null ? user.getId() + " " + user.getName() : "NA"));
	}

}
