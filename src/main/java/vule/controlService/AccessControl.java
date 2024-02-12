package vule.controlService;


	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import vule.dao.UserDao;

	public class AccessControl {

	    private static UserDao userDao = new UserDao();

	    public static boolean isAdminstrator(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession(false);
	        if (session != null && session.getAttribute("username") != null) {
	            String username = (String) session.getAttribute("username");
	            return userDao.checkAdminStatus(username);
	        } else {
	            return false;
	        }
	    }
	    
	}


