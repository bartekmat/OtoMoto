package com.sda.controller;

import com.sda.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserBlockController", value = "/blockUser")
public class UserBlockController extends HttpServlet {

    UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        userRepository.blockUser(email);
        response.sendRedirect("/users");
    }
}