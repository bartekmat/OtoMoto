package com.sda.filter;

import com.sda.service.AdService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebFilter("/all")
public class SearchParamsValidationFilter implements Filter {
    private static final String PRICE_PARAMETERS_ERROR_MESSAGE = "invalid price parameters";
    private static final String MILEAGE_PARAMETERS_ERROR_MESSAGE = "invalid mileage parameters";
    private static final String YEAR_PARAMETERS_ERROR_MESSAGE = "invalid year parameters";
    private static final String MIN_PRICE_INIT = "0";
    private static final String MAX_PRICE_INIT = "9999999";
    private static final String MIN_MILEAGE_INIT = "0";
    private static final String MAX_MILEAGE_INIT = "9999999";
    private static final String MIN_YEAR_INIT = "0";
    private static final String MAX_YEAR_INIT = "9999999";
    private static final String COMPANY_INIT = "any";

    private AdService adService = AdService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String priceMin = Optional.ofNullable(request.getParameter("priceMin")).orElse("");
        String priceMax = Optional.ofNullable(request.getParameter("priceMax")).orElse("");
        String mileageMin = Optional.ofNullable(request.getParameter("mileageMin")).orElse("");
        String mileageMax = Optional.ofNullable(request.getParameter("mileageMax")).orElse("");
        String yearMin = Optional.ofNullable(request.getParameter("yearMin")).orElse("");
        String yearMax = Optional.ofNullable(request.getParameter("yearMax")).orElse("");
        String company = Optional.ofNullable(request.getParameter("company")).orElse(COMPANY_INIT);

        boolean priceParamsOk = validateParams("price", priceMin, priceMax, MIN_PRICE_INIT, MAX_PRICE_INIT, PRICE_PARAMETERS_ERROR_MESSAGE, request);
        boolean mileageParamsOk = validateParams("mileage", mileageMin, mileageMax, MIN_MILEAGE_INIT, MAX_MILEAGE_INIT, MILEAGE_PARAMETERS_ERROR_MESSAGE, request);
        boolean yearParamsOk = validateParams("year", yearMin, yearMax, MIN_YEAR_INIT, MAX_YEAR_INIT, YEAR_PARAMETERS_ERROR_MESSAGE, request);

        if (priceParamsOk && mileageParamsOk && yearParamsOk){
            request.setAttribute("company", company);
            System.out.println("all parameters are valid - request attributes set");
        }
        else {
            request.setAttribute("companies", adService.getAllCompanies());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("allAds.jsp");
            requestDispatcher.forward(request,response);
            request.setAttribute("ads", new ArrayList<>());
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    private boolean validateParams(String parameterName, String min, String max, String minInit, String maxInit, String errorMessage, HttpServletRequest request){
        if (min.isEmpty()){
            min = minInit;
        }
        if (max.isEmpty()){
            max = maxInit;
        }
        if (areValidNumbers(min, max)){
            request.setAttribute(parameterName+"Min", Integer.valueOf(min));
            request.setAttribute(parameterName+"Max", Integer.valueOf(max));
            return true;
        }else {
            request.setAttribute(parameterName+"ParamError", errorMessage);
            return false;
        }

    }
    private boolean areValidNumbers(String min, String max){
        try {
            double lowerBound = Integer.parseInt(min);
            double higherBound = Integer.parseInt(max);

            return lowerBound>=0 && higherBound>=0 && lowerBound <= higherBound;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
