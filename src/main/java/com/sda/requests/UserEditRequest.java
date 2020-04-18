package com.sda.requests;

import lombok.Value;

@Value
public class UserEditRequest {

    String name;
    String surname;
    String email;

    public UserEditRequest(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
