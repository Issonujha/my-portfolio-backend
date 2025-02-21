package com.example.myportfolio.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	
	private String iden;
	
	@Nonnull
	private String email;
	
	private String nameLowerCase;
	
	private Long contact;
	
	private String address;
	
	private String thumb;
	
}
