package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Produit;
import service.IProduitService;
import service.impl.ProduitServiceImpl;

@WebServlet("/addProduit")
public class AddProduitServlet extends HttpServlet {

    private IProduitService produitService = ProduitServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nom         = req.getParameter("nom");
            String description = req.getParameter("description");
            double prix        = Double.parseDouble(req.getParameter("prix"));
            produitService.addProduit(new Produit(nom, description, prix));
            resp.sendRedirect(req.getContextPath() + "/listProduits");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Le prix doit être un nombre valide.");
            req.setAttribute("listeProduits", produitService.getAllProduits());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}