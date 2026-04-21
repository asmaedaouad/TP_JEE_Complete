package dao;

import model.Client;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static List<Client> clients = new ArrayList<>();

    
    public void addClient(Client client) {
        clients.add(client);
    }

   
    public List<Client> getAllClients() {
        return clients;
    }

     
    public Client getClientByName(String nom) {
        for (Client c : clients) {
            if (c.getNom().equalsIgnoreCase(nom)) {
                return c;
            }
        }
        return null;
    }
    
    
    public Client getClientByEmail(String email) {
        for (Client c : clients) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }
    
}