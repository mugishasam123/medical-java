package com.samuel.users.controllers;


import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samuel.utils.Json;
import com.samuel.utils.Message;
import com.samuel.utils.Response;
import com.samuel.users.models.User;

@WebServlet("/login")
public class SigninController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new Json().parseBodyJson(req, User.class);
        try {
            Message<String> result = user.login(user.getEmail(), user.getPassword());
            Response.send(res, result, HttpServletResponse.SC_OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Response.send(res, new Message<>(e.getMessage(), null), HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
