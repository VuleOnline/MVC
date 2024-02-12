package vule.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vule.controlService.AccessControl;
import vule.dao.TestDao;
import vule.dao.UserDao;
import vule.model.Test;
import vule.model.User;


@WebServlet("/")
public class ListsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;
	private TestDao testDao;
	 User user;

	public void init() {
		userDao = new UserDao();
		testDao = new TestDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = AccessControl.isAdminstrator(request, response);
		request.setAttribute("admin", isAdmin);
		
		String option=request.getServletPath();
		
		switch(option) 
		{
		/*KORINICI*/
		case "/allusers":
			selectAll(request, response);//napravljeno
			break;
		case "/userlist":
			selectUsers(request, response);//napravljeno
			break;
		case "/adminlist":
			selectAdmins(request, response);//napravljeno
			break;
		case "/usereditform":
			userEditForm(request, response);//sredjeno
			break;
		case "/edituser":
			userEdit(request, response);//sredjeno
			break;
		case "/deleteuser":
			deleteUser(request, response);//napravljeno
			break;
		
			
			
			/*TESTOVI*/
		
		case "/testlist":
			selectScope(request, response);//sredjeno - mozda
			break;
		case "/edittest":
			editTest(request, response);//sredjeno
			break;
		case "/testform":
			getTechQ(request, response);
			break;
		case "/questioneditform":
			showQuestionForm(request, response);//sredjeno
			break;	
		case "/finish":
			finishTest(request, response);//napravljeno
			break;
		case "/insertquestion":/*iz question forma*/
			insertQuestion(request, response);//napravljeno
			break;
		case "/deletetest":
			deleteTest(request, response);//napravljeno
			break;
		case "/deletequestion":
			deleteQuestion(request, response);//napravljeno
			break;
			
		default:
			RequestDispatcher rd = request.getRequestDispatcher("form/login.jsp");
			rd.forward(request, response);
			
			
		}
		
		
	}
				
					
					/*-----------------USERS MANIPULATION---------------*/
	
	/*LISTA SVIH REGISTRIVANIH KORISNIKA*/
	private void selectAll(HttpServletRequest request, HttpServletResponse response) {
		List<User> allUsers = userDao.SelectAll();
		request.setAttribute("allUsers", allUsers);
		RequestDispatcher rd = request.getRequestDispatcher("list/userlist.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*LISTA REGISTRIVANIH  samo KORISNIKA*/
	private void selectUsers(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userDao.selectUsers();
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("list/userlist.jsp"); 
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*LISTA REGISTRIVANIH ADMINA*/
	private void selectAdmins(HttpServletRequest request, HttpServletResponse response) {
		List<User> admins = userDao.sellectAllAdmins();
		request.setAttribute("admins", admins);
		RequestDispatcher rd = request.getRequestDispatcher("list/userlist.jsp"); 
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*REGISTRACIONA FORMA ZA EDIT PODATAKA KORSINIKA*/
	private void userEditForm(HttpServletRequest request, HttpServletResponse response) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		User userById = userDao.selectUserById(userid);
		request.setAttribute("user", userById);
		RequestDispatcher rd = request.getRequestDispatcher("form/registrationform.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/*EDITOVANJE PODATAKA KORISNIKA*/
	private void userEdit(HttpServletRequest request, HttpServletResponse response) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		String userfname = request.getParameter("userfname");
        String userlname = request.getParameter("userlname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String getAdmin = request.getParameter("admin");
        boolean admin = "da".equals(getAdmin);
       user = new User(userid, userfname, userlname, username, password, admin);
       userDao.updateUser(user);
       System.out.println("******Stiglo posle update********");
       try {
    	   response.sendRedirect(request.getContextPath() + "/list/userlist.jsp");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	/*BRISANJE KORISNIKA*/
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		userDao.deleteUser(userid);
		try {
			response.sendRedirect("list/userlist.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
								/*------TESTS MANIPULATION---------*/
	
	

	
	
	
	
	
	/*SELEKTUJE SVE- GRUPISE PO OBLASTI PA IZBACUJE DUPLIKATE*/
	private void selectScope(HttpServletRequest request, HttpServletResponse response) {
		List<Test> scope = testDao.selectScope();
		request.setAttribute("scope", scope);
		RequestDispatcher rd = request.getRequestDispatcher("list/testlist.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*EDIT PODATAKA TESTA*/
	private void editTest(HttpServletRequest request, HttpServletResponse response) {
		int testid = Integer.parseInt(request.getParameter("testid"));
		String tehnologija = request.getParameter("tehnologija");
		String pitanje =request.getParameter("pitanje");
		String odgStr = request.getParameter("odgovori");
		List<String> odgovoriSplit= Arrays.asList(odgStr.split(","));
		List<String> odgovori = odgovoriSplit.stream()
			.map(String::trim).map(String::toLowerCase)
			.collect(Collectors.toList());
		String tacan = request.getParameter("tacan");//Ovo ne postoji u formi i trebaju izmene
		Test test = new Test(testid, tehnologija, pitanje, odgovori,tacan);
		testDao.updateTest(test);
		try {
			response.sendRedirect(request.getContextPath() + "/list/testlist.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*TEST FORMA ZA EDIT PODATAKA NA TESTU*/
	private void showQuestionForm(HttpServletRequest request, HttpServletResponse response) {
		int id =Integer.parseInt(request.getParameter("testid"));
		Test test = testDao.selectTestById(id);
		request.setAttribute("test", test);
		RequestDispatcher rd = request.getRequestDispatcher("form/questionform.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*UBACIVANJE NOVOG pitanja*/
	private void insertQuestion(HttpServletRequest request, HttpServletResponse response) {
		String tehnologija = request.getParameter("tehnologija").trim().toUpperCase();
		String pitanje = request.getParameter("pitanje").trim().toLowerCase();
		String odgovoriStr = request.getParameter("odgovori");
		List<String> odgovoriSplit= Arrays.asList(odgovoriStr.split(","));
		List<String> odgovori = odgovoriSplit.stream()
			.map(String::trim).map(String::toLowerCase)
			.collect(Collectors.toList());
		String tacan = request.getParameter("tacan").trim().toLowerCase();
		Test test = new Test(tehnologija, pitanje, odgovori, tacan); 
		if(testDao.insertQuestion(test)>0)
		{
			request.setAttribute("INSERTMSG", "Question succesfully inserted.");
		}
		else request.setAttribute("INSERTMSG", "Failed question isertion");	
		
		RequestDispatcher rd = request.getRequestDispatcher("form/questionform.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("Catch insert pitanja");
			e.printStackTrace();
		}
		
	}
	
	private void deleteTest(HttpServletRequest request, HttpServletResponse response) {
		String teh = request.getParameter("tehnologija");
		testDao.deleteTest(teh);
		try {
			response.sendRedirect("list/testlist.jsp");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	/*-----------------VRACA LISTU PITANJA JEDNE TEHNOOGIJE-----------------*/
	private void getTechQ(HttpServletRequest request, HttpServletResponse response) {
		String teh = request.getParameter("tehnologija");
		List<Test> tehtest = testDao.getTechQ(teh);
		HttpSession session = request.getSession();
		session.setAttribute("tehtest", tehtest);
		RequestDispatcher rd = request.getRequestDispatcher("form/testform.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("getTechQ catch block.");
			e.printStackTrace();
		}
		
	}
	
	private void finishTest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		 if(session!=null)
		 session.removeAttribute("tehtest");
		 RequestDispatcher rd = request.getRequestDispatcher("home/userhome.jsp");
		 try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		}
		

		    
		  
		    
		

	private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) {
		int testid = Integer.parseInt(request.getParameter("testid"));
		testDao.deleteQuestion(testid);
		try {
			response.sendRedirect("testlist");
		} catch (IOException e) {
			System.out.println("Question delete catch block.ListController");
			e.printStackTrace();
		}
		
	}
		
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
