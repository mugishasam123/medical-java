package com.samuel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samuel.utils.ApiResponse;
import com.samuel.utils.Response;

@WebServlet(urlPatterns = "")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response.send(resp, new ApiResponse<>("Welcome to the medical information management api", null),
                HttpServletResponse.SC_OK);
    }

}
