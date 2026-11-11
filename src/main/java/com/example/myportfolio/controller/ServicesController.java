package com.example.myportfolio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myportfolio.dto.CommercialServiceDTO;
import com.example.myportfolio.dto.InquiryRequestDTO;
import com.example.myportfolio.dto.ServiceQuoteRequest;
import com.example.myportfolio.dto.ServiceQuoteResponse;
import com.example.myportfolio.entity.ServiceInquiry;
import com.example.myportfolio.service.CommercialServiceService;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "*")
public class ServicesController {

    private final CommercialServiceService commercialServiceService;

    public ServicesController(CommercialServiceService commercialServiceService) {
        this.commercialServiceService = commercialServiceService;
    }

    @GetMapping("/all")
    public List<CommercialServiceDTO> getAllServices() {
        return commercialServiceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercialServiceDTO> getService(@PathVariable String id) {
        return commercialServiceService.getServiceById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/calculate-quote")
    public ResponseEntity<ServiceQuoteResponse> calculateQuote(@RequestBody ServiceQuoteRequest request) {
        ServiceQuoteResponse response = commercialServiceService.calculateQuote(request);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/inquire")
    public ResponseEntity<ServiceInquiry> submitInquiry(@RequestBody InquiryRequestDTO request) {
        if (request.getClientName() == null || request.getClientEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        ServiceInquiry saved = commercialServiceService.saveInquiry(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/inquiries")
    public List<ServiceInquiry> getAllInquiries() {
        return commercialServiceService.getAllInquiries();
    }

    @PutMapping("/inquiries/{id}/status")
    public ResponseEntity<ServiceInquiry> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        String notes = body.get("notes");
        return commercialServiceService.updateInquiryStatus(id, status, notes)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
