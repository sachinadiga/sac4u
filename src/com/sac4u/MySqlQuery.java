package com.sac4u;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlQuery {
	String insertQuery = "INSERT INTO sac4u.apps (app_name,category,download_link,r_code,icon_path,create_ts)"
			+ "values ('ph1','ph2','ph3','ph4','ph5','ph6')";
	
	String selectQuery = "SELECT * from sac4u.apps";
	String deleteQuery = "DELETE FROM sac4u.apps where <condition>";
	
    Statement stmt;
    Connection con;
	public void connectToDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sac4u","root","admin");  
			stmt=con.createStatement();
			System.out.println("[INFO]:Connected to DB");
		}catch(ClassNotFoundException e){
			System.out.println("[ERROR]: com.mysql.jdbc.Driver not found. Add corresponding .jar to build path.");
		}catch(SQLException e){
			System.out.println("[ERROR]: Connection coudn't be established: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean exModifyQuery(String query){
//		System.out.println("[INFO]:"+query);
		connectToDB();
		try {
			stmt.execute(query);
			con.close();
			System.out.println("[INFO]:Disconnected from DB");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ResultSet exSelectQuery(String query){
		connectToDB();
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery(query);
//			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
//		System.out.println("[INFO]:Disconnected from DB");
		return resultSet;
	}
	
}
