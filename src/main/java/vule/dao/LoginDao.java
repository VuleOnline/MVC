package vule.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vule.model.Login;
import vuleutils.JDBCUtils;

public class LoginDao {


	public boolean checkLogin(Login userlogin) {
		boolean status = false;
		
		String QUSER = "SELECT * FROM user WHERE username=? AND password=?";
		try 
			(Connection con = JDBCUtils.getConnection();
			PreparedStatement pst = con.prepareStatement(QUSER);){
			pst.setString(1, userlogin.getUsername());
			pst.setString(2, userlogin.getPassword());
			ResultSet rs = pst.executeQuery();
			status = rs.next();
		}
			catch(Exception e) 
		{
				e.printStackTrace();
		}
		return status;
	}

	public boolean checkAdmin(Login userlogin) {
		boolean status = false;
		
		String QADMIN ="SELECT * FROM user WHERE username =? and password = ? and admin=1";
		try
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(QADMIN);){
			pst.setString(1, userlogin.getUsername());
			pst.setString(2, userlogin.getPassword());
			ResultSet rs = pst.executeQuery();
			status = rs.next();
		}
		catch(Exception e) 
	{
			e.printStackTrace();
	}
		
		return status;
	}
	

}
