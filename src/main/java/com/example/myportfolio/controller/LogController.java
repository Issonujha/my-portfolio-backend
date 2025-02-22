package com.example.myportfolio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController {
	
	private static final String LOG_FILE_PATH = "logs/docker.log";
	
	@GetMapping
    public String getLogs() {
        try {
            String logs = new String(Files.readAllBytes(Paths.get(LOG_FILE_PATH)));
            return logs.replace("\n", "<br>");
        } catch (IOException e) {
            return "Error reading log file: " + e.getMessage();
        }
    }

}
