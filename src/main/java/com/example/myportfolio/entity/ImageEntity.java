package com.example.myportfolio.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "image_entity")
@Data
@Builder
public class ImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Nonnull
	private String awsUrl;

	private String description;

	@Nonnull
	private String fileName;
	
	@Nonnull
	private Long size;
	
	@Nonnull
	private String userId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

}
