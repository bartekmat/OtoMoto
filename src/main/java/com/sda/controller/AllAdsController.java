package com.sda.controller;

import com.sda.service.AdService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowAllAdsController", value = "/all")
public class ShowAllAdsController extends HttpServlet {

    private AdService adService = AdService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer minPrice = (Integer) request.getAttribute("priceMin");
        Integer maxPrice = (Integer) request.getAttribute("priceMax");
        Integer minMileage = (Integer) request.getAttribute("mileageMin");
        Integer maxMileage = (Integer) request.getAttribute("mileageMax");
        Integer minYear = (Integer) request.getAttribute("yearMin");
        Integer maxYear = (Integer) request.getAttribute("yearMax");

        String company = (String) request.getAttribute("company");
        String sort = "random";

        //if we get here we can savely call search - all parameters should be valid
        //during first loading or without parameters set my user all are set to initial value
        request.setAttribute("ads", adService.getAllAdsFiltered(
                        minPrice, 
                        maxPrice, 
                        minMileage, 
                        maxMileage, 
                        minYear, 
                        maxYear, 
                        company, 
                        sort));

        request.setAttribute("companies", adService.getAllCompanies());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("allAds.jsp");
        requestDispatcher.forward(request,response);
    }
}
