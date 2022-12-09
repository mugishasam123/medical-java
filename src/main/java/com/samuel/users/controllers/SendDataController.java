package com.samuel.users.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.samuel.utils.Message;
import com.samuel.utils.ParseJwt;
import com.samuel.utils.Excel2Json;
import com.samuel.utils.Response;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@WebServlet("/data")
public class SendDataController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            final String authorizationHeaderValue = req.getHeader("Authorization");
            String token = null;
            if (authorizationHeaderValue == null) {
                throw new Exception("jwt token is required");
            }
            token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length());

            Message<JSONObject> results = null;
            Jws<Claims> claims = ParseJwt.parseJwt(token);
            String userRole = (String) claims.getBody().get("role");
            String fileName = "C:\\Users\\SAMI\\Documents\\projects\\medical-java\\src\\main\\java\\com\\samuel\\db\\MedicalData.xlsx";
            System.out.println(userRole);

            if (userRole.equals("ADMIN")) {
                results = Excel2Json.AdminData(fileName, 3);
                System.out.println(results);
            } else if (userRole.equals("PATIENT")) {
                results = Excel2Json.usersData(fileName, 0);
                System.out.println(results);
            } else if (userRole.equals("PHYSICIAN")) {
                results = Excel2Json.usersData(fileName, 1);
                System.out.println(results);
            } else if (userRole.equals("PHARMACIST")) {
                results = Excel2Json.usersData(fileName, 2);
                System.out.println(results);
            }

            Response.send(res, results, HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Response.send(res, new Message<>(e.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
