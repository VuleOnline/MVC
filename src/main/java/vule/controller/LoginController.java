package vule.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import vule.dao.LoginDao;
import vule.model.Login;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	LoginDao loginDao;
 
	public void init() 
  {
	  loginDao = new LoginDao();
  }
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Da l se izvrsi");
		response.sendRedirect("form/login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login userlogin= new Login();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		userlogin.setUsername(username);
		userlogin.setPassword(password);

		
			if(loginDao.checkLogin(userlogin)) 
			{
				 boolean isAdmin = loginDao.checkAdmin(userlogin);
				 
				 if(isAdmin) 
					{
					 HttpSession sess = request.getSession();
					 sess.setAttribute("admin", isAdmin);
					 System.out.println("Dodelio admina");
					 response.sendRedirect("home/adminhome.jsp");
					}else 
					{
					HttpSession sess = request.getSession();
					sess.setAttribute("username", userlogin.getUsername());
					System.out.println("Dodelio username");
					response.sendRedirect("home/userhome.jsp");
					}
				 
			}else 
			{
				request.setAttribute("LOGMSG", "Log in Failed.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("form/login.jsp");
				dispatcher.forward(request, response);
			}
			
	
	}

}
