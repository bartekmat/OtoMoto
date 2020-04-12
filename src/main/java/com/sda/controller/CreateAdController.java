package com.sda.controller;

import com.sda.model.Ad;
import com.sda.model.Car;
import com.sda.model.User;
import com.sda.service.AdService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet(name = "CreateAdController", value = "/createAd")
public class CreateAdController extends HttpServlet {

    AdService adService = AdService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createAd.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ad newAdd = createAdFrom(
                (User) request.getSession().getAttribute("user"),
                createCarFrom(
                        request.getParameter("company"),
                        request.getParameter("model"),
                        request.getParameter("mileage"),
                        request.getParameter("year")
                ),
                request.getParameter("price")
        );

        adService.saveAd(newAdd);

        request.getRequestDispatcher("home.jsp").forward(request,response);
    }

    private Ad createAdFrom(User owner, Car car, String price){
        return Ad.builder()
                .user(owner)
                .car(car)
                .price(Integer.parseInt(price))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
    private Car createCarFrom(String company, String model, String mileage, String year){
        return Car.builder()
                .company(company)
                .model(model)
                .mileage(Integer.parseInt(mileage))
                .year(Integer.parseInt(year))
                .build();
    }
}
