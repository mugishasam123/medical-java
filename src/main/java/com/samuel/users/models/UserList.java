package com.samuel.users.models;

import java.util.ArrayList;
import java.util.List;

import com.samuel.db.Db;
import com.samuel.users.types.UserTypes;

public class UserList {

    public static List<User> fetchUsers() {
        ArrayList<User> users = new ArrayList<User>(Db.getUsers());
        ArrayList<User> usersFiltered = new ArrayList<User>();

        for (User user : users) {
            if (!user.getRole().equals(UserTypes.ADMIN)) {
                usersFiltered.add(user);
            }
        }
        return usersFiltered;
    }
}
