package com.sda.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Builder
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private Boolean isBlocked;

    @ManyToMany
    @JoinTable(name = "users_ads", joinColumns = @JoinColumn(name = "observing_user_id"), inverseJoinColumns = @JoinColumn(name = "ad_id"))
    private List<Ad> ads;

    public User(Integer id, String name, String surname, String email, String password, Boolean isBlocked, List<Ad> ads) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.ads = ads;
    }

    public User(String name, String surname, String email, String password, Boolean isBlocked, List<Ad> ads) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.ads = ads;
    }
}
