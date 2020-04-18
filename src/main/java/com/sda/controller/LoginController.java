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
import java.util.Optional;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private static final String LOGIN_ERROR_MESSAGE = "Wrong email or password";

    private UserService userService = UserService.userService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<User> user = userService.loginUser(
                req.getParameter("email"),
                req.getParameter("password"));
        if(user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            req.getRequestDispatcher("home.jsp").forward(req,resp);
        } else {
            req.setAttribute("loginError", LOGIN_ERROR_MESSAGE);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
