package com.samuel.models;

import java.util.regex.Pattern;
import com.samuel.utils.ApiResponse;
import com.samuel.db.Database;

public class Patient extends User {
    @Override
    public ApiResponse<User> register() throws Exception {
        if (!Pattern.matches("^\\d{6}$", getPassword())) {
            throw new Exception("Password must be 6 digits");
        }
        encrptPass();
        Database.addUser(this);

        return new ApiResponse<User>("You been registered as patient successfully", Database.findUser(getEmail()));
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
