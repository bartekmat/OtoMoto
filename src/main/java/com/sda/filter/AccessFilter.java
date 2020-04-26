package com.sda.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)  throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false); //do not create new session if there is none, just check
        String homeURI = request.getContextPath()+"/";
        String loginURI = request.getContextPath()+"/login";
        String registerURI = request.getContextPath()+"/register";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean homeRequest = request.getRequestURI().equals(homeURI);
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);

        if (loggedIn || homeRequest || loginRequest || registerRequest) {
            System.out.println("filter success");
            chain.doFilter(request, response);
        } else {
            System.out.println("failed");
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
