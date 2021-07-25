package com.dao;

import java.sql.*;

public class MyConnection {

	public Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String uname = "system";
			String pass = "12345";
			
			con = DriverManager.getConnection(url,uname,pass);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return con;
		
		
	}
}
