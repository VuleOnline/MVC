package vule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutContoller() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
		HttpSession sess = request.getSession(false);
		if(sess!=null) {
		sess.removeAttribute("username");
		sess.removeAttribute("admin");
		sess.invalidate();
		}
		response.sendRedirect("form/login.jsp");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
