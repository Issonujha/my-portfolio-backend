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
public class CommercialServiceDTO {
    private String id;
    private String name;
    private String headline;
    private String category;
    private String description;
    private int basePrice;
    private int baseDeliveryDays;
    private String badge;
    private List<String> techStack;
    private List<String> deliverables;
    private List<ServiceFeatureDTO> features;
}
