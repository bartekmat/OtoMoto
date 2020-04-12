package com.sda.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "ads")
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

    public Ad(Integer id, User user, Car car, Integer price) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.price = price;
    }

    public Ad(User user, Car car, Integer price) {
        this.user = user;
        this.car = car;
        this.price = price;
    }
}
