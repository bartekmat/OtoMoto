package com.sda.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String company;
    private String model;
    private Integer mileage;
    private Integer year;

    public Car(Integer id, String company, String model, Integer mileage, Integer year) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
    }

    public Car(String company, String model, Integer mileage, Integer year) {
        this.company = company;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
    }
}
