package com.gestion.service.impl;

import com.gestion.dao.IUserDAO;
import com.gestion.dao.impl.UserDAOImpl;
import com.gestion.model.User;
import com.gestion.service.IUserService;

public class UserServiceImpl implements IUserService {
    
    // PLUS DE SINGLETON !
    private IUserDAO userDAO;
    
    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();  // Nouvelle instance
    }
    
    @Override
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        
        User user = userDAO.findByUsername(username);
        
        if (user == null) return null;
        if (!user.getPassword().equals(password)) return null;
        
        return user;
    }
    
    @Override
    public boolean register(String username, String password, String email, String phone, String role) {
        if (username == null || username.trim().isEmpty()) return false;
        if (password == null || password.length() < 4) return false;
        
        if (userDAO.existsByUsername(username)) return false;
        
        if (role == null || role.trim().isEmpty()) {
            role = "USER";
        }
        
        User newUser = new User(username, password, email, phone, role);
        userDAO.save(newUser);
        
        return true;
    }
}