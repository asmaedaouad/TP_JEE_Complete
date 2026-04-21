package web;

import model.User;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
 
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
	private UserService userservice;
	
	@Override
	public void init() throws ServletException {
		userservice = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/views/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(userservice.login(username, password)) {
			HttpSession session = request.getSession();
			User user = userservice.getUser(username);
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
		} else {
			request.setAttribute("error", "Invalid username or password");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}
}