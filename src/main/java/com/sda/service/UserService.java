package com.sda.service;

import com.sda.model.User;
import com.sda.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService userService;

    private UserRepository userRepository;

    public static UserService userService(){
        if (userService == null){
            userService = new UserService(UserRepository.getInstance());
        }
        return userService;
    }

    public boolean registerUser(User user){
        return userRepository.saveUser(user);
    }

    public boolean loginUser(String email, String password, HttpServletRequest request){
        Optional<User> user = userRepository.canLogin(email, password);
        if (user.isPresent()){
            request.getSession().setAttribute("user", user.get());
            return true;
        }else {
            return false;
        }
    }





}
