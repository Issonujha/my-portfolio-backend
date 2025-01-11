package com.example.myportfolio.dto;

import lombok.Data;

@Data
public class ProjectRequest {

	/**
	 * For project name
	 */
	private String projectName;

	/**
	 * Skills required
	 */
	private String skills;

	/**
	 * If there is any backend project then back end url is shown
	 */
	private String projectLinkBackEnd;

	/**
	 * If there is any front end part then frontend Public url is shown
	 */
	private String projectLinkFrontEnd;

	/**
	 * This is the thumbnail of the project
	 */
	private String thumbnail;

	/**
	 * This link is for all emages related to project.
	 * 
	 * Comma seperated
	 */
	private String reference;
	
	
	/**
	 * For Description of project
	 */
	private String description;

}
