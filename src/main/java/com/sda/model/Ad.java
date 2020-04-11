package com.sda.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Ads")
@Data
@NoArgsConstructor
@Builder
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    private User owner;

    @OneToOne(targetEntity = Car.class)
    private Car car;
    private Integer price;
    private LocalDateTime createdAt;

    public Ad(Integer id, User owner, Car car, Integer price, LocalDateTime createdAt) {
        this.id = id;
        this.owner = owner;
        this.car = car;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Ad(User owner, Car car, Integer price, LocalDateTime createdAt) {
        this.owner = owner;
        this.car = car;
        this.price = price;
        this.createdAt = createdAt;
    }
}
