package com.example.myportfolio.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project-item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectItem  {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;
	private String thumbnail;
	private String backendUrl;
	private String frontendUrl;
	private String hint;
	private Date createdTime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project-item-details-id", referencedColumnName = "id")
	@JsonManagedReference
	private ProjectItemDetails projectItemDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

}