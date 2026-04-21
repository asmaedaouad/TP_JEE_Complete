package service.impl;

import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import model.User;
import service.IUserService;

public class UserServiceImpl implements IUserService {

    private static UserServiceImpl instance;
    private IUserDAO userDAO = UserDAOImpl.getInstance();

    private UserServiceImpl() {}

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }

    @Override
    public User login(String username, String password) {
        User u = userDAO.findByUsername(username);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }

    @Override
    public boolean register(String username, String password,
                            String email, String phone, String role) {
        if (userDAO.existsByUsername(username)) return false;
        if (role == null || role.trim().isEmpty()) role = "USER";
        userDAO.save(new User(username, password, email, phone, role));
        return true;
    }
}