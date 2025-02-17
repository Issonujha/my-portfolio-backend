package com.example.myportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myportfolio.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, String>{

}
