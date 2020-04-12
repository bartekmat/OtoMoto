package com.sda.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Ads")
@Data
@NoArgsConstructor
@Builder
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Car.class)
    private Car car;

    private Integer price;
    private Timestamp createdAt;

    public Ad(Integer id, User user, Car car, Integer price, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Ad(User user, Car car, Integer price, Timestamp createdAt) {
        this.user = user;
        this.car = car;
        this.price = price;
        this.createdAt = createdAt;
    }
}
