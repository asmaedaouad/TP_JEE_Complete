package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import model.User;
import service.UserService;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/views/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");
        String phone    = request.getParameter("phone");
       
        if(userService.getUser(username) != null) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }
        
        User newUser = new User(username, password, email, phone);
        userService.register(newUser);
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
}