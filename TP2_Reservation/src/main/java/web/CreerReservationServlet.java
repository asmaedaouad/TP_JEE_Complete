package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.ReservationService;
import java.io.IOException;

@WebServlet("/CreerReservationServlet")
public class CreerReservationServlet extends HttpServlet {

    private ReservationService service = new ReservationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        
        String type = request.getParameter("type");
        String prix = request.getParameter("prix");
        String vue = request.getParameter("vue");

       
        String error = service.ajouterReservation(
                nom, prenom, telephone, email,
                type, prix, vue
        );

        if (error != null) {
            
            request.setAttribute("error", error);
            request.getRequestDispatcher("Reservation.jsp").forward(request, response);
        } else {
            
            HttpSession session = request.getSession();
            session.setAttribute("reservations", service.getAllReservations());
            request.setAttribute("success", "Réservation ajoutée avec succès !");
            request.setAttribute("reservations", service.getAllReservations());
            request.getRequestDispatcher("infoReservation.jsp").forward(request, response);
        }
    }
}