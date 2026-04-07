package service;

import dao.ClientDao;
import model.Client;
import java.util.List;

public class ClientService {

    private ClientDao clientDao = new ClientDao();

    // Ajouter un client avec validation
    public String ajouterClient(String nom, String prenom, String telephone, String email) {

        
        if(nom == null || nom.isEmpty() ||
           prenom == null || prenom.isEmpty() ||
           telephone == null || telephone.isEmpty() ||
           email == null || email.isEmpty()) {

            return "Tous les champs sont obligatoires !";
        }

        Client client = new Client(nom, prenom, telephone, email);

        clientDao.addClient(client);

        return null; 
    }

    // Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    
}