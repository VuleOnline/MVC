package vuleutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	
	public static Connection getConnection(){
		Connection con=null;
	String url="jdbc:mysql://localhost:3306/managment";
	String username ="root";
	String password="root";
	System.out.println("Konektovano");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
	} catch (ClassNotFoundException e) {
		System.out.println("JDBCUtils - Class.forName(...)");
		e.printStackTrace();
	}
	catch (SQLException e) {
		System.out.println("JDBCUTils - Connection con = DriverManager.");
		e.printStackTrace();
		}
		
		return con;
	}
}
