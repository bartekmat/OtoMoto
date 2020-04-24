package com.sda.service;

import com.sda.model.Ad;
import com.sda.model.User;
import com.sda.repository.AdRepository;
import com.sda.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService userService;

    private UserRepository userRepository;
    private AdRepository adRepository;

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService(UserRepository.getInstance(), AdRepository.getInstance());
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

    public List<Ad> addObservedAd(String email, String ad_id) {
        return adRepository.addToObservedList(email, ad_id);
    }

    public void removeObservedAd(String email, String ad_id) {
        adRepository.removeFromObservedList(email,ad_id);
    }
}
