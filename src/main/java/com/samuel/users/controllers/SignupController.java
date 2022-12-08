package com.samuel.users.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samuel.utils.Message;
import com.samuel.utils.Json;
import com.samuel.utils.Response;
import com.samuel.users.types.UserTypes;
import com.samuel.users.models.Admin;
import com.samuel.users.models.Patient;
import com.samuel.users.models.Pharmacist;
import com.samuel.users.models.Physician;
import com.samuel.users.models.User;

@WebServlet("/register")
public class SignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new Json().parseBodyJson(req, User.class);

        try {
            Message<User> results = null;
            if (user.getRole() == UserTypes.ADMIN) {
                Admin admin = new Admin();
                admin.fromUser(user);
                results = admin.register();
            } else if (user.getRole() == UserTypes.PATIENT) {
                Patient patient = new Patient();
                patient.fromUser(user);
                results = patient.register();
            } else if (user.getRole() == UserTypes.PHARMACIST) {
                Pharmacist pharmacist = new Pharmacist();
                pharmacist.fromUser(user);
                results = pharmacist.register();
            } else if (user.getRole() == UserTypes.PHYSICIAN) {
                Physician physician = new Physician();
                physician.fromUser(user);
                results = physician.register();
            }

            Response.send(res, results, HttpServletResponse.SC_CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            Response.send(res, new Message<>(e.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
