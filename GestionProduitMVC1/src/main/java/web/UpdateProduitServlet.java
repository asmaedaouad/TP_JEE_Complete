package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Produit;
import service.IProduitService;
import service.impl.ProduitServiceImpl;

@WebServlet("/updateProduit")
public class UpdateProduitServlet extends HttpServlet {

    private IProduitService produitService = ProduitServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long   id          = Long.parseLong(req.getParameter("idProduit"));
            String nom         = req.getParameter("nom");
            String description = req.getParameter("description");
            double prix        = Double.parseDouble(req.getParameter("prix"));

            Produit p = new Produit(nom, description, prix);
            p.setId(id);
            produitService.updateProduit(p);
            resp.sendRedirect(req.getContextPath() + "/listProduits");

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Données invalides.");
            req.setAttribute("listeProduits", produitService.getAllProduits());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}