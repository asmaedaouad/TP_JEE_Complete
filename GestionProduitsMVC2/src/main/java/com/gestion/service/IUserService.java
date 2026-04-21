package com.gestion.service;

import com.gestion.model.User;

public interface IUserService {
    User login(String username, String password);
    boolean register(String username, String password, String email, String phone, String role);
}