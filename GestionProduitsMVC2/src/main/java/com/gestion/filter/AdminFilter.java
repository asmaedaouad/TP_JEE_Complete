package com.gestion.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String action = request.getParameter("action");
        
        // Vérifier si l'action nécessite des droits admin
        boolean needsAdmin = "add".equals(action) || "delete".equals(action) || 
                            "edit".equals(action) || "update".equals(action);
        
        if (!needsAdmin) {
            chain.doFilter(req, resp);
            return;
        }
        
        HttpSession session = request.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        
        if ("ADMIN".equals(role)) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/produit?erreur=acces_refuse");
        }
    }
}