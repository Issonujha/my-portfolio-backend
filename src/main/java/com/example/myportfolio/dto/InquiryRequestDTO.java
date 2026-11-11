package com.example.myportfolio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryRequestDTO {
    private String clientName;
    private String clientEmail;
    private String serviceId;
    private String serviceName;
    private List<String> selectedFeatures;
    private Integer estimatedDays;
    private Integer estimatedCost;
    private String targetDeadline;
    private String notes;
}
