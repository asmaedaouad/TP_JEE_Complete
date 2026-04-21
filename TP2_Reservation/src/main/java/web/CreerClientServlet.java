package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.ClientService;
import java.io.IOException;

@WebServlet("/CreerClientServlet")
public class CreerClientServlet extends HttpServlet {

    private ClientService service = new ClientService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

       
        String error = service.ajouterClient(nom, prenom, telephone, email);

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("Inscription.jsp").forward(request, response);
        } else {
            
            HttpSession session = request.getSession();
            session.setAttribute("clients", service.getAllClients());
            request.setAttribute("success", "Client ajouté avec succès !");
            request.setAttribute("clients", service.getAllClients());
            request.getRequestDispatcher("infoClient.jsp").forward(request, response);
        }
    }
    
    
}