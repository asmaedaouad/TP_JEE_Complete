package dao.impl;

import dao.IUserDAO;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private static UserDAOImpl instance;
    private List<User> users = new ArrayList<>();
    private Long compteur = 1L;

    private UserDAOImpl() {
        // Comptes par défaut pour les tests
        User admin = new User("admin", "admin123", "admin@test.com", "0600000001", "ADMIN");
        admin.setId(compteur++);
        users.add(admin);

    }

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) instance = new UserDAOImpl();
        return instance;
    }

    @Override
    public void save(User user) {
        user.setId(compteur++);
        users.add(user);
    }

    @Override
    public User findByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }
}