package com.example.myportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceFeatureDTO {
    private String id;
    private String name;
    private String description;
    private int additionalPrice;
    private int additionalDays;
    private boolean isPopular;
}
