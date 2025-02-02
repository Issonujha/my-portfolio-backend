package com.example.myportfolio.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.MailRequest;
import com.example.myportfolio.dto.ProjectRequest;
import com.example.myportfolio.entity.ProjectItem;
import com.example.myportfolio.entity.ProjectItemDetails;
import com.example.myportfolio.exceptions.ProjectNotFountException;
import com.example.myportfolio.repository.ProjectRepository;

import jakarta.mail.MessagingException;

@Service
public class ProjectServices {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmailService emailService;
	
	private Logger logger = LoggerFactory.getLogger(ProjectServices.class);

	public List<ProjectItem> getAll() {
		return projectRepository.findAll();
	}

	public ProjectItem getProjectById(String id) {
		return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFountException("No Project Item found."));
	}

	public void addCurrentProject(ProjectRequest projectRequest) {
		ProjectItem projectItem = ProjectItem.builder().name(projectRequest.getProjectName()).createdTime(new Date())
				.backendUrl(projectRequest.getProjectLinkBackEnd()).frontendUrl(projectRequest.getProjectLinkFrontEnd())
				.thumbnail(projectRequest.getThumbnail())
				.projectItemDetails(ProjectItemDetails.builder().description(projectRequest.getDescription()).build())
				.build();
		projectRepository.save(projectItem);
		try {
			emailService.sendMailToMeFromWeb(MailRequest.builder().to("sjha9563@gmail.com").body(
					"Hi, \n Project have been added to db an another charge. \n \n Thanks And Regards,\n Team Pluto \n")
					.build());
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	public void deleteProject(String id) {
		ProjectItem projectItem = getProjectById(id);
		if (projectItem != null) {
			projectRepository.delete(projectItem);
		}
	}

}
