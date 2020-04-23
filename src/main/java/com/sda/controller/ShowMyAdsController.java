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
public class ShowMyAdsController extends HttpServlet {

    private AdService adService = AdService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user");
        request.setAttribute("myAds", adService.getAdsByUser(currentUser.getId()));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("myAds.jsp");
        requestDispatcher.forward(request,response);
    }
}
