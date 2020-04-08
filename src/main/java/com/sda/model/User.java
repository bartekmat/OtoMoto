package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
}
