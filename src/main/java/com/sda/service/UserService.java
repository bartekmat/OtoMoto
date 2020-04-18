package com.sda.service;

import com.sda.model.User;
import com.sda.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService userService;

    private UserRepository userRepository;

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService(UserRepository.getInstance());
        }
        return userService;
    }

    public boolean registerUser(User user){
        return userRepository.saveUser(user);
    }

    public Optional<User> loginUser(String email, String password){
        return userRepository.canLogin(email, password);
    }


    public List<User> findAll() {
        return userRepository.getAllUsers();
    }

    public void addObservedAd(String email, String ad_id) {
        userRepository.updateObservedList(email, ad_id);
    }
}
