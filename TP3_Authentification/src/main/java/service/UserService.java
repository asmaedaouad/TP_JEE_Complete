package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO userDao;

    // constructeur
    public UserService() {
        this.userDao = new UserDAO();
    }

    // Inscription 
    public boolean register(User user) {
        
        if(userDao.exists(user.getUsername())) {
            return false; 
        }
        
        userDao.addUser(user);
        return true;
    }

    // Connexion 
    public boolean login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return true; 
        }
        return false; 
    }

    // Récupérer l’utilisateur complet
    public User getUser(String username) {
        return userDao.getUserByUsername(username);
    }
}