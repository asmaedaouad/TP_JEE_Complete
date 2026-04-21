package com.gestion.dao;

import com.gestion.model.User;

public interface IUserDAO {
    void save(User user);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}