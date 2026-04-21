package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.User;
import service.IUserService;
import service.impl.UserServiceImpl;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private IUserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Si déjà connecté → aller directement à la liste
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/listProduits");
            return;
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            // Créer la session et stocker l'utilisateur + son rôle
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/listProduits");
        } else {
            req.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}