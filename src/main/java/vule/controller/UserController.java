package vule.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vule.controlService.AccessControl;
import vule.dao.UserDao;
import vule.model.User;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   UserDao userDao;
   User user;
   boolean isAdmin;
	public void init() {
		userDao = new UserDao();
	}
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		isAdmin = AccessControl.isAdminstrator(request, response);
		request.setAttribute("admin", isAdmin);	
		response.sendRedirect(request.getContextPath() + "/form/registrationform.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userfname = request.getParameter("userfname");
        String userlname = request.getParameter("userlname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String getAdmin = request.getParameter("admin");
        boolean admin = "da".equals(getAdmin);
		
        user = new User(userfname, userlname, username, password, admin);
        
		if(userDao.insert(user)==1)
		{
			if(isAdmin) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("list/userlist.jsp");
				rd.forward(request, response);
			}
			else {
			HttpSession ses = request.getSession();
			ses.setAttribute("username", user.getUsername());
			RequestDispatcher rd = request.getRequestDispatcher("home/userhome.jsp");
			rd.forward(request, response);
			}
		}else {
			request.setAttribute("REGMSG", "Registration Failed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("form/registrationform.jsp");
			dispatcher.forward(request, response);
		}
	}

}
