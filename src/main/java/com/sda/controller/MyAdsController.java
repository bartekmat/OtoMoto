package com.sda.controller;

import com.sda.model.User;
import com.sda.service.AdService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowMyAdsController", value = "/my")
public class MyAdsController extends HttpServlet {

    private AdService adService = AdService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("myAds", adService.getAdsByUser((User) request.getSession().getAttribute("user")));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("myAds.jsp");
        requestDispatcher.forward(request,response);
    }
}
