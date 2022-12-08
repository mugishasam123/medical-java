package com.samuel.users.models;

import java.util.ArrayList;
import java.util.List;

import com.samuel.db.Db;

public class UserList {

    public static List<User> fetchUsers() {
        return new ArrayList<User>(Db.getUsers());
    }
}
