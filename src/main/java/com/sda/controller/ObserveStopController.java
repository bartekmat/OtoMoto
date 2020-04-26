package com.sda.controller;

import com.sda.model.Ad;
import com.sda.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ObserveStopController", value = "/stopObserve")
public class ObserveStopController extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String ad_id = request.getParameter("ad");

        userService.removeObservedAd(email, ad_id);

        response.sendRedirect(request.getContextPath()+"/all");
    }
}
