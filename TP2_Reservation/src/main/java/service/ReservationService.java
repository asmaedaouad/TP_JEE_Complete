package service;

import dao.ReservationDao;
import model.Client;
import model.Reservation;
import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao = new ReservationDao();

    // Ajouter une réservation avec validation
    public String ajouterReservation(String nom, String prenom, String telephone, String email,
                                     String type, String prixStr, String vue) {

        
        if(nom == null || nom.isEmpty() ||
           prenom == null || prenom.isEmpty() ||
           telephone == null || telephone.isEmpty() ||
           email == null || email.isEmpty() ||
           type == null || type.isEmpty() ||
           prixStr == null || prixStr.isEmpty() ||
           vue == null || vue.isEmpty()) {

            return "Tous les champs sont obligatoires !";
        }

        double prix;

        try {
            prix = Double.parseDouble(prixStr);
        } catch (NumberFormatException e) {
            return "Le prix doit être un nombre valide !";
        }

        
        Client client = new Client(nom, prenom, telephone, email);

        Reservation reservation = new Reservation(client, type, prix, vue);

        reservationDao.addReservation(reservation);

        return null;
    }

    // Récupérer  les réservations
    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }
}