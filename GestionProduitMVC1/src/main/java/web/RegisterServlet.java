package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import service.IUserService;
import service.impl.UserServiceImpl;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private IUserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");
        String phone    = req.getParameter("phone");

        boolean succes = userService.register(username, password, email, phone, "USER");

        if (succes) {
            req.setAttribute("success", "Compte créé avec succès ! Connectez-vous.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Ce nom d'utilisateur est déjà utilisé.");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}