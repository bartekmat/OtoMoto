package com.sda.repository;

import com.sda.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

    private static UserRepository userRepository;

    private List<User> users;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository(new ArrayList<>());
            //here init -> to be removed when db is set up
            User defaultUser = new User("Tim", "Buchalka", "tim@buchalka.au", "tim");
            userRepository.save(defaultUser);
        }
        return userRepository;
    }

    public boolean save(User user) {
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findAny();
        if(existingUser.isPresent()) {
            return false;
        }
        users.add(user);
        System.out.println(users);
        return true;
    }

    public Optional<User> canLogin(String email, String password){
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .filter(u -> u.getPassword().equals(password))
                .findAny();
        return existingUser;
    }
    public User getDefaultUser(){
        return users.get(0);
    }
}
