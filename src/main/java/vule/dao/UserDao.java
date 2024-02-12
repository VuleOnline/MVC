package vule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import vule.model.User;
import vuleutils.JDBCUtils;

public class UserDao {
	
	
	
	public int insert(User user) 
	{
		System.out.println("da l dodje dovde");
		String INSERT_SQL="INSERT INTO user (userid, userfname, userlname, username, password, admin)"
				+ " VALUES (NULL,?,?,?,?,?)";
		int result = 0;
		try 
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(INSERT_SQL);){
		pst.setString(1, user.getUserfname());
		pst.setString(2, user.getUserlname());
		pst.setString(3, user.getUsername());
		pst.setString(4, user.getPassword());
		pst.setBoolean(5, user.isAdmin());
		result = pst.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Neuspelo insertovanje. UserDao - pst.executeUpdate();");
			e.printStackTrace();
		}
		
				
			return result;
		
	}

public boolean checkAdminStatus(String username) 
{
	String ADMIN_SQL="SELECT admin FROM user WHERE username=?"; 
	
	try
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(ADMIN_SQL);){
	pst.setString(1, username);
	try (ResultSet rs = pst.executeQuery()) {
	  if (rs.next()) {
	     return rs.getBoolean("admin");
	       }
	   }
	 } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
			
}

public List<User> sellectAllAdmins() {
	String ADMINS_SQL = "SELECT * FROM user WHERE admin = 1";
	List<User> admins = new ArrayList<>();
	try
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(ADMINS_SQL);){
	ResultSet rs = pst.executeQuery();
	while(rs.next()) 
	{
		int userid = rs.getInt("userid");
		String userfname = rs.getString("userfname");
		String userlname = rs.getString("userlname");
		String username = rs.getString("username");
		String password = rs.getString("password");
		Boolean admin = rs.getBoolean("admin");
		admins.add(new User(userid, userfname, userlname, username, password, admin));	
	}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return admins;
}

public List<User> SelectAll() {
	String ALLUSERS_SQL = "SELECT * FROM user";
	List<User> allUsers = new ArrayList<>();
	try 
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(ALLUSERS_SQL);){
	ResultSet rs = pst.executeQuery();
	while(rs.next()) 
	{
		int userid = rs.getInt("userid");
		String userfname = rs.getString("userfname");
		String userlname = rs.getString("userlname");
		String username = rs.getString("username");
		String password = rs.getString("password");
		Boolean admin = rs.getBoolean("admin");
		allUsers.add(new User(userid, userfname, userlname, username, password, admin));
	}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return allUsers;
}

public List<User> selectUsers() {
	String USERS_SQL = "SELECT * FROM user WHERE admin = 0";
	List<User> users = new ArrayList<>();
	try 
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(USERS_SQL);){
	ResultSet rs = pst.executeQuery();
	while(rs.next()) 
	{
		int userid = rs.getInt("userid");
		String userfname = rs.getString("userfname");
		String userlname = rs.getString("userlname");
		String username = rs.getString("username");
		String password = rs.getString("password");
		Boolean admin = rs.getBoolean("admin");
		users.add(new User(userid, userfname, userlname, username, password, admin));
	}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return users;
}

public User selectUserById(int id) {
	String USER_SQL="SELECT * FROM user WHERE userid=?";
	User user = null;
	
	
	try 
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(USER_SQL);){
	pst.setInt(1, id);
	ResultSet rs = pst.executeQuery();
	while(rs.next()) 
	{
		int userid = rs.getInt("userid");
		String userfname = rs.getString("userfname"); 
		String userlname = rs.getString("userlname");
		String username= rs.getString("username");
		String password= rs.getString("password");
		Boolean admin = rs.getBoolean("admin"); 
		user = new User(userid, userfname, userlname, username, password, admin);
	}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return user;

}

public boolean deleteUser(int id) {
	
	String USER_DELETE_SQL = "DELETE FROM user WHERE userid=?";
	boolean result =false;
	
	try
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(USER_DELETE_SQL);){
	pst.setInt(1, id);
	result = pst.executeUpdate()>0;
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return result;
}

public boolean updateUser(User user) {
	String UPDATE_User_SQL="UPDATE user SET userfname = ?, userlname = ?, username = ?, password = ?, admin = ? WHERE userid=?";
	boolean success = false;
	
	try
	(Connection con = JDBCUtils.getConnection();
	PreparedStatement pst = con.prepareStatement(UPDATE_User_SQL);){
	pst.setString(1, user.getUserfname());
	pst.setString(2, user.getUserlname());
	pst.setString(3, user.getUsername());
	pst.setString(4, user.getPassword());
	pst.setBoolean(5, user.isAdmin());
	pst.setInt(6, user.getUserid());
	success = pst.executeUpdate()>0;
	System.out.println("gotovo");
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return success;

}


}
