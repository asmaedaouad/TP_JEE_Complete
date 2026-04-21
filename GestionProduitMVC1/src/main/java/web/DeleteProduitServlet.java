package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import service.IProduitService;
import service.impl.ProduitServiceImpl;

@WebServlet("/deleteProduit")
public class DeleteProduitServlet extends HttpServlet {

    private IProduitService produitService = ProduitServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            produitService.deleteProduit(id);
        } catch (NumberFormatException ignored) {}
        resp.sendRedirect(req.getContextPath() + "/listProduits");
    }
}