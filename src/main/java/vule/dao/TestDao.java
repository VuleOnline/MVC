package vule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vule.model.Test;
import vuleutils.JDBCUtils;

public class TestDao {

	/*SELEKTUJE SVE- GRUPISE PO OBLASTI PA IZBACUJE DUPPLIKATE*/
	public List<Test> selectScope() {
		String SCOPE_SQL = "SELECT DISTINCT tehnologija FROM test";
		List<Test> scope = new ArrayList<>();
		try 
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(SCOPE_SQL);){
		ResultSet rs = pst.executeQuery();
		System.out.println("Izvrsava se");
		while(rs.next())
		{
			String tehnologija = rs.getString("tehnologija");
			scope.add(new Test(tehnologija));
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scope;
		
	}

	public Test selectTestById(int id) {
		
		Test test = null;
		String UPDATE_SQL = "SELECT * FROM test WHERE testid=?";
		
		try 
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(UPDATE_SQL);){
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) 
		{
			int testid = rs.getInt("testid");
			String tehnologija = rs.getString("tehnologija"); 
			String pitanje= rs.getString("pitanje");
			String odgovoriStr= rs.getString("odgovori");
			List<String> odgovori = Arrays.asList(odgovoriStr.split(","));
			String tacan = rs.getString("tacan"); 
			test = new Test(testid, tehnologija, pitanje, odgovori, tacan);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return test;
	}

	public int insertQuestion(Test test) {
		String INSERT_TEST_SQL="INSERT INTO test VALUES(NULL, ?,?,?,?)";
		int result = 0;
		try
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(INSERT_TEST_SQL);){
		pst.setString(1, test.getTehnologija());
		pst.setString(2, test.getPitanje());
		String ansReady = String.join(",", test.getOdgovori());
		pst.setString(3, ansReady);
		pst.setString(4, test.getTacan());
		result = pst.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public boolean updateTest(Test test) {
		String UPDATE_TEST_SQL="UPDATE test SET tehnologija = ?, pitanje = ?, odgovori = ?, tacan = ? WHERE testid=?";
		boolean success = false;
		
		try
		(Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(UPDATE_TEST_SQL);){
		pst.setString(1, test.getTehnologija());
		pst.setString(2, test.getPitanje());
		String ansReady = String.join(",", test.getOdgovori());
		pst.setString(3, ansReady);//problem
		pst.setString(4, String.valueOf(test.getTacan()));
		pst.setInt(5, test.getTestid());
		success = pst.executeUpdate()>0;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

	public void deleteTest(String teh) {
		String DELETE_TEST_SQL = "DELETE FROM test WHERE tehnologija = ?";
	    try (Connection con = JDBCUtils.getConnection();
	         PreparedStatement pst = con.prepareStatement(DELETE_TEST_SQL)) {
	        pst.setString(1, teh);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
		
	}

	public List<Test> getTechQ(String teh) {
		String testTehSQL = "SELECT * FROM test WHERE tehnologija=?";
		List<Test> techQ = new ArrayList<>();
		try(
		Connection con = JDBCUtils.getConnection();
		PreparedStatement pst = con.prepareStatement(testTehSQL);){
		pst.setString(1, teh);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) 
			{
				int testid = rs.getInt("testid");
				String tehnologija = rs.getString("tehnologija"); 
				String pitanje= rs.getString("pitanje");
				String odgovoriStr = rs.getString("odgovori");
				List<String> odgovori = Arrays.asList(odgovoriStr.split(","));
				String tacan =rs.getString("tacan");
				techQ.add(new Test(testid, tehnologija, pitanje, odgovori, tacan));
			}
		}catch(SQLException e) {}
		return techQ;
		
	}

	public void deleteQuestion(int testid) {
		String deleteQ_SQL="DELETE FROM test WHERE testid = ?";
		try 
			(Connection con = JDBCUtils.getConnection();
			PreparedStatement pst = con.prepareStatement(deleteQ_SQL);){
			pst.setInt(1, testid);
			pst.executeUpdate();
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	

}
