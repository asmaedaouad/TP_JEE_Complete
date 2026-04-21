package com.gestion.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.gestion.model.Produit;
import com.gestion.model.User;
import com.gestion.service.IProduitService;
import com.gestion.service.IUserService;
import com.gestion.service.impl.ProduitServiceImpl;
import com.gestion.service.impl.UserServiceImpl;

@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
    
    private IProduitService produitService;
    private IUserService userService;
    
    @Override
    public void init() throws ServletException {
        
        produitService = new ProduitServiceImpl();
        userService = new UserServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) action = "list";
        
        switch (action) {
            case "login":
                showLogin(req, resp);
                break;
            case "register":
                showRegister(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "delete":
                deleteProduit(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                listProduits(req, resp);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) action = "list";
        
        switch (action) {
            case "login":
                processLogin(req, resp);
                break;
            case "register":
                processRegister(req, resp);
                break;
            case "add":
                addProduit(req, resp);
                break;
            case "update":
                updateProduit(req, resp);
                break;
            default:
                listProduits(req, resp);
                break;
        }
    }
    
    // ========== PRODUITS METHODS ==========
    
    private void listProduits(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String idParam = req.getParameter("idProduit");
        List<Produit> liste;
        
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                Produit p = produitService.getProduitById(Long.parseLong(idParam));
                if (p != null) {
                    liste = Arrays.asList(p);
                } else {
                    liste = produitService.getAllProduits();
                    req.setAttribute("error", "Aucun produit trouvé avec l'ID " + idParam);
                }
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
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Produit p = produitService.getProduitById(id);
            if (p != null) {
                req.setAttribute("produitEdit", p);
            } else {
                req.setAttribute("error", "Produit introuvable.");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID invalide.");
        }
        listProduits(req, resp);
    }
    
    private void addProduit(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            String nom = req.getParameter("nom");
            String description = req.getParameter("description");
            double prix = Double.parseDouble(req.getParameter("prix"));
            
            produitService.addProduit(new Produit(nom, description, prix));
            resp.sendRedirect(req.getContextPath() + "/produit");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Le prix doit être un nombre valide.");
            listProduits(req, resp);
        }
    }
    
    private void updateProduit(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("idProduit"));
            String nom = req.getParameter("nom");
            String description = req.getParameter("description");
            double prix = Double.parseDouble(req.getParameter("prix"));
            
            Produit p = new Produit(nom, description, prix);
            p.setId(id);
            produitService.updateProduit(p);
            resp.sendRedirect(req.getContextPath() + "/produit");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Données invalides.");
            listProduits(req, resp);
        }
    }
    
    private void deleteProduit(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            produitService.deleteProduit(id);
        } catch (NumberFormatException ignored) {}
        resp.sendRedirect(req.getContextPath() + "/produit");
    }
    
    // ========== AUTH METHODS ==========
    
    private void showLogin(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/produit");
            return;
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
    
    private void processLogin(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        User user = userService.login(username, password);
        
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/produit");
        } else {
            req.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
    
    private void showRegister(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }
    
    private void processRegister(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        
        boolean success = userService.register(username, password, email, phone, "USER");
        
        if (success) {
            req.setAttribute("success", "Compte créé avec succès ! Connectez-vous.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Ce nom d'utilisateur est déjà utilisé ou mot de passe trop court (min 4).");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
    
    private void logout(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/produit?action=login");
    }
}