package edu.phystech.task1.storage;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static Map<String, String> storage = null;

    public static Map<String, String> getInstance() {
        if (storage == null) {
            storage = new HashMap<>();
        }
        return storage;
    }

    public static void addUser(User user) {
        UserStorage.getInstance().put(user.getUsername(), user.getPassword());
    }

    public static boolean isUsernameExist(String username) {
        return UserStorage.getInstance().containsKey(username);
    }

    public static String getUserPassword(String username) {
        return UserStorage.getInstance().get(username);
    }
}
