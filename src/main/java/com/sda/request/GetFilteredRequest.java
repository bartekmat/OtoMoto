package com.sda.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetFilteredRequest {
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minMileage;
    private Integer maxMileage;
    private Integer minYear;
    private Integer maxYear;
    private String company;
    private String sort;
}
