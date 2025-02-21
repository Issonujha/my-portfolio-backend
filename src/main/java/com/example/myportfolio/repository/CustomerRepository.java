package com.example.myportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myportfolio.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
