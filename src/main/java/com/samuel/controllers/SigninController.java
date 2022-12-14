package com.samuel.controllers;

import java.io.IOException;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samuel.models.Admin;
import com.samuel.models.User;
import com.samuel.utils.Json;
import com.samuel.utils.ApiResponse;
import com.samuel.utils.Response;

@WebServlet("/login")
public class SigninController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new Json().parseBodyJson(req, Admin.class);
        try {
            ApiResponse<String> result = user.login(user.getEmail(), user.getPassword());
            Response.send(res, result, HttpServletResponse.SC_OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Response.send(res, new ApiResponse<>(e.getMessage(), null), HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
