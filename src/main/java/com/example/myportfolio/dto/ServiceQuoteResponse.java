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
public class ServiceQuoteResponse {
    private String serviceId;
    private String serviceName;
    private int basePrice;
    private int baseDeliveryDays;
    private int totalCost;
    private int totalDeliveryDays;
    private List<String> includedFeatures;
    private List<String> deliverables;
}
