package dao;

import java.util.Map;
import java.util.HashMap;
import model.User;

public class UserDAO{
	
	private static Map<String,User> users= new HashMap<>();
	
	// Ajouter User
	
	public void addUser(User user) {
		users.put(user.getUsername(), user);
	}
	
	
	 // récupérer un utilisateur par username
	
	
	public User getUserByUsername(String username) {
		return users.get(username);
		
	}
	
	 // vérifier si utilisateur existe
	
	public boolean exists(String username) {
		
		return users.containsKey(username);
		
	}
	
}