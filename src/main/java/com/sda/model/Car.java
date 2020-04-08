package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Car {
    private String company;
    private String model;
    private Integer mileage;
    private Integer year;
}
