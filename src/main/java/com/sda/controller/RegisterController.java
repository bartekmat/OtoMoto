package com.sda.controller;

import com.sda.model.User;
import com.sda.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = createUserFrom(req);
        boolean created = userService.registerUser(user);
        if(created) {
            req.getRequestDispatcher("home.jsp").forward(req,resp);
        } else {
            req.setAttribute("loginExists", user.getEmail());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private User createUserFrom(HttpServletRequest request){
        return User.builder()
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .isBlocked(false)
                .build();
    }
}
