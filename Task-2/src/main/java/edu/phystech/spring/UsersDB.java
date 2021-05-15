package edu.phystech.spring;

import java.util.HashMap;
import java.util.Map;

public class UsersDB {
    private Map<String, String> usersDB = new HashMap<>();

    public void addUser(String username, String password) {
        usersDB.put(username, password);
    }

    public boolean isUsernameExist(String username) {
        return usersDB.containsKey(username);
    }

    public String getUserPassword(String username) {
        return usersDB.get(username);
    }
}
