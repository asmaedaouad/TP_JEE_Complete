package dao;

import model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    // Stockage en mémoire
    private static List<Reservation> reservations = new ArrayList<>();

    // Ajouter une réservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Récupérer les réservations
    public List<Reservation> getAllReservations() {
        return reservations;
    }
}