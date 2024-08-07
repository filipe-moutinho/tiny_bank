package com.tiny.bank.user.repository;

import com.tiny.bank.exception.ResourceAlreadyExistsException;
import com.tiny.bank.user.model.User;
import org.springframework.lang.NonNull;

import java.util.HashMap;

public class UserRepository {

    private final static HashMap<String, User> USERS = new HashMap<>();

    private static UserRepository instance = null;

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public User get(@NonNull String name) {
        return USERS.get(name);
    }

    public User save(@NonNull User user) {
        if (USERS.containsKey(user.getName())) {
            throw new ResourceAlreadyExistsException("User already exists");
        }
        USERS.put(user.getName(), user);
        return user;
    }
}
