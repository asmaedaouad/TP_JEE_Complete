package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import model.Produit;
import service.IProduitService;
import service.impl.ProduitServiceImpl;

@WebServlet("/listProduits")
public class ListProduitServlet extends HttpServlet {

    private IProduitService produitService = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("idProduit");
        List<Produit> liste;

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                Produit p = produitService.getProduitById(Long.parseLong(idParam));
                liste = (p != null) ? Arrays.asList(p) : new ArrayList<>();
                if (p == null) req.setAttribute("error", "Aucun produit trouvé avec l'ID " + idParam);
            } catch (NumberFormatException e) {
                liste = produitService.getAllProduits();
                req.setAttribute("error", "ID invalide.");
            }
        } else {
            liste = produitService.getAllProduits();
        }

        req.setAttribute("listeProduits", liste);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}