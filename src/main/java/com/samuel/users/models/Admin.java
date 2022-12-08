package com.samuel.users.models;

import java.util.regex.Pattern;
import com.samuel.utils.Message;
import com.samuel.db.Db;
import com.samuel.users.interfaces.IUser;

public class Admin extends User implements IUser {
    @Override
    public Message<User> register() throws Exception {
        if (!Pattern.matches("^\\d{10}$", getPassword())) {
            throw new Exception("Password must be 10 digits");
        }
        encrptPass();
        Db.addUser(this);
        return new Message<User>("Admin success", Db.findUser(getEmail()));
    }

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
