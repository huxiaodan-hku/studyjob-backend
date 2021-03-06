package com.example.redistemplate.dao.spec;

import com.example.redistemplate.entities.User;

public interface UserDao {
    User findByUsername(String username);

    void createNewUser(User user);

    boolean existsByUsername(String username);

}
