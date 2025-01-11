package com.example.myportfolio.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.ProjectRequest;
import com.example.myportfolio.entity.ProjectItem;
import com.example.myportfolio.entity.ProjectItemDetails;
import com.example.myportfolio.exceptions.ProjectNotFountException;
import com.example.myportfolio.repository.ProjectRepository;

@Service
public class ProjectServices {

	@Autowired
	private ProjectRepository projectRepository;

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
	}

	public void deleteProject(String id) {
		ProjectItem projectItem = getProjectById(id);
		if (projectItem != null) {
			projectRepository.delete(projectItem);
		}
	}

}
