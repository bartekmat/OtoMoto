package com.sda.controller;

import com.sda.model.Ad;
import com.sda.service.AdService;
import com.sda.service.FilterValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ShowAllAdsController", value = "/all")
public class ShowAllAdsController extends HttpServlet {
    private static final String FILTER_ERROR_MESSAGE = "fix your filters dude, something is wrong";

    private AdService adService = AdService.getInstance();
    private FilterValidator filterValidator = FilterValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //making sure that input is always a string
        String minPriceString   = Optional.ofNullable(request.getParameter("minPrice")).orElse("");
        String maxPriceString   = Optional.ofNullable(request.getParameter("maxPrice")).orElse("");
        String minMileageString = Optional.ofNullable(request.getParameter("minMileage")).orElse("");
        String maxMileageString = Optional.ofNullable(request.getParameter("maxMileage")).orElse("");
        String minYearString = Optional.ofNullable(request.getParameter("minYear")).orElse("");
        String maxYearString = Optional.ofNullable(request.getParameter("maxYear")).orElse("");
        String company = Optional.ofNullable(request.getParameter("company")).orElse("any");
        String sort = Optional.ofNullable(request.getParameter("sort")).orElse("---");

        //assigning values in case of blank input
        if (minPriceString.isBlank()) minPriceString = "0";
        if (maxPriceString.isBlank()) maxPriceString = String.valueOf(Double.MAX_VALUE);
        if (minMileageString.isBlank()) minMileageString = "0";
        if (maxMileageString.isBlank()) maxMileageString = String.valueOf(Integer.MAX_VALUE);
        if (minYearString.isBlank()) minYearString = "0";
        if (maxYearString.isBlank()) maxYearString = String.valueOf(LocalDate.now().getYear());

        //parsing to proper format
        Double minPrice = 0d;
        Double maxPrice = 0d;
        Integer minMileage = 0;
        Integer maxMileage = 0;
        Integer minYear = 0;
        Integer maxYear = 0;

        try { //HERE WE TRY TO PARSE ALL NUMERIC VALUES, IF WE FAIL -> ERROR
            minPrice = Double.parseDouble(minPriceString);
            maxPrice = Double.parseDouble(maxPriceString);
            minMileage = Integer.parseInt(minMileageString);
            maxMileage = Integer.parseInt(maxMileageString);
            minYear = Integer.parseInt(minYearString);
            maxYear = Integer.parseInt(maxYearString);

            //HERE WE CHECK HOW PAIRS OF VALUES RELATE TO ONE ANOTHER, FAIL -> ERROR
            if (filterValidator.validatePriceFilter(minPrice, maxPrice) ||
                    filterValidator.validateMileageFilter(minMileage,maxMileage)||
                        filterValidator.validateYearFilter(minYear, maxYear)){
                request.setAttribute("ads", adService.getAllAdsFiltered(minPrice, maxPrice, minMileage, maxMileage, minYear, maxYear, company, sort));
            }else throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("filterError", FILTER_ERROR_MESSAGE);
            request.setAttribute("ads", List.of());
        }

    //set correct view -> redirect back
        request.setAttribute("companies", adService.getAllCompanies());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("allAds.jsp");
        requestDispatcher.forward(request,response);
    }
}
