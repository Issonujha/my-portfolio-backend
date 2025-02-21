package com.example.myportfolio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project-item-details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectItemDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "reference_url")
	private String references;
	
	@OneToOne(mappedBy = "projectItemDetails")
	@JsonBackReference
	private ProjectItem projectItem;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

}
