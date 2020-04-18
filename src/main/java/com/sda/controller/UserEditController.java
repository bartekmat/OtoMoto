package com.sda.controller;

import com.sda.repository.UserRepository;
import com.sda.requests.UserEditRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEditController", value = "/editUser")
public class UserEditController extends HttpServlet {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("doget "+email);
        request.setAttribute("editedUsersEmail", email);
        request.getRequestDispatcher("userEdit.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String editedUsersEmail = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        UserEditRequest editRequest = new UserEditRequest(name, surname, editedUsersEmail);

        userRepository.editUserByEmail(editRequest);
        response.sendRedirect("/users");
    }
}
