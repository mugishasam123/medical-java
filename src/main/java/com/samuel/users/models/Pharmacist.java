package com.samuel.users.models;

import java.util.regex.Pattern;

import com.samuel.utils.Message;
import com.samuel.db.Db;
import com.samuel.users.interfaces.IUser;

public class Pharmacist extends User implements IUser {
    @Override
    public Message<User> register() throws Exception {
        if (!Pattern.matches("^\\d{4}$", getPassword())) {
            throw new Exception("Password must be 4 digits");
        }
        encrptPass();
        Db.addUser(this);

        return new Message<User>("You been registered as pharmacist successfully", Db.findUser(getEmail()));
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
