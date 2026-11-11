package com.example.myportfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myportfolio.entity.ServiceInquiry;

@Repository
public interface ServiceInquiryRepository extends JpaRepository<ServiceInquiry, String> {
    List<ServiceInquiry> findAllByOrderByCreatedAtDesc();
    List<ServiceInquiry> findByStatus(String status);
}
