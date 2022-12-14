package com.samuel.models;

import java.util.regex.Pattern;

import com.samuel.utils.ApiResponse;
import com.samuel.db.Database;

public class Pharmacist extends User {
    @Override
    public ApiResponse<User> register() throws Exception {
        if (!Pattern.matches("^\\d{4}$", getPassword())) {
            throw new Exception("Password must be 4 digits");
        }
        encrptPass();
        Database.addUser(this);

        return new ApiResponse<User>("You been registered as pharmacist successfully", Database.findUser(getEmail()));
    }

    @Override
    public void fromUser(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setAge(user.getAge());
        setRole(user.getRole());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setGender(user.getGender());
    }
}
