package com.example.myportfolio.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.ProjectRequest;
import com.example.myportfolio.entity.ProjectItem;
import com.example.myportfolio.service.ProjectServices;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
	
	private ProjectServices projectServices;
	
	public ProjectsController(ProjectServices projectServices) {
		this.projectServices = projectServices;
	}
	
	@GetMapping("/all")
	public List<ProjectItem> getAll() {
		return projectServices.getAll();
	}
	
	@GetMapping("/get/{id}")
	public ProjectItem getProjectItem(@PathVariable String id) {
		return projectServices.getProjectById(id);
	}
	
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public String addProjects(@RequestBody ProjectRequest projectRequest) {
		projectServices.addCurrentProject(projectRequest);
		return "Project added successfully.";
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteProjectById(@PathVariable String id) {
		projectServices.deleteProject(id);
		return "Project deleted successsfully.";
	}

}
