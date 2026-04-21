package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Produit;
import service.IProduitService;
import service.impl.ProduitServiceImpl;

@WebServlet("/editProduit")
public class EditProduitServlet extends HttpServlet {

    private IProduitService produitService = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long id    = Long.parseLong(req.getParameter("id"));
            Produit p  = produitService.getProduitById(id);
            if (p != null) {
                req.setAttribute("produitEdit", p);
            } else {
                req.setAttribute("error", "Produit introuvable.");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID invalide.");
        }
        req.setAttribute("listeProduits", produitService.getAllProduits());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}