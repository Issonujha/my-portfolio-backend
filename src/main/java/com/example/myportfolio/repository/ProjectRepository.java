package com.example.myportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myportfolio.entity.ProjectItem;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectItem, String>{

}
