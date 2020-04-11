package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor
@Builder
public class Ad {

    private User owner;
    private Car car;
    private Integer price;
    private LocalDateTime createdAt;
}
