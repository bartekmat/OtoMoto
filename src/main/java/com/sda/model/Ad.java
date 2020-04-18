package com.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "ads")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Ad(Integer id, User user, Car car, Integer price, List<User> users) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.price = price;
        this.users = users;
    }

    public Ad(User user, Car car, Integer price, List<User> users) {
        this.user = user;
        this.car = car;
        this.price = price;
        this.users = users;
    }
}
