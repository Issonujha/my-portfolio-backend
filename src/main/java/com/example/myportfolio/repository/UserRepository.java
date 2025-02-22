package com.example.myportfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myportfolio.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByUsername(String username);
	
	List<User> findByCustomerId(String customerId);

}
