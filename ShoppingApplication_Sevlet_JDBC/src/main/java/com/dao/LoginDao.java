package com.dao;

import java.sql.*;

import com.model.Login;

public class LoginDao {

	Connection con = null;
	MyConnection mycon = new MyConnection();
	PreparedStatement pstate;
	Statement state;
	
	public String AdminLogin(Login log) {
		
		con = mycon.getConnection();
		String str = null;
		try {
			pstate=con.prepareStatement("select * from admindb where AdminUsername = ?");
			
			pstate.setString(1, log.getUname());
			
			ResultSet rs = pstate.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(3).equals(log.getPass())){
					System.out.println(log.getUname()+" Logged In");
					return log.getUname();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
		
	}
	
	public String CustomerLogin(Login log) {
		
		con = mycon.getConnection();
		String str = null;
		try {
			pstate=con.prepareStatement("select * from customerdb where CustUsername = ?");
			
			pstate.setString(1, log.getUname());
			
			ResultSet rs = pstate.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(6).equals(log.getPass())){
					System.out.println(log.getUname()+" Logged In");
					return log.getUname();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
		
	}
	
}
