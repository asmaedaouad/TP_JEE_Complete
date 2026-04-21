package com.gestion.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String action = request.getParameter("action");
        
        // Exclure login et register de l'authentification
        if ("login".equals(action) || "register".equals(action)) {
            chain.doFilter(req, resp);
            return;
        }
        
        HttpSession session = request.getSession(false);
        boolean connected = session != null && session.getAttribute("user") != null;
        
        if (connected) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/produit?action=login");
        }
    }
}