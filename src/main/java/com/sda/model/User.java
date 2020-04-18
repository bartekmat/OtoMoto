package com.sda.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public User(Integer id, String name, String surname, String email, String password, Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public User(String name, String surname, String email, String password, Boolean isBlocked) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
    }
}
