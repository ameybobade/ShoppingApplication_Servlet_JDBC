package com.dao;

import java.sql.*;

import com.model.Registration;

public class RegistrationDao {
	
	Connection con = null;
	MyConnection mycon = new MyConnection();
	PreparedStatement pstate;
	Statement state;
	int i=0;
	
	public int AdminRegistration(Registration reg) {
		
		con = mycon.getConnection();
		int id = 0;
		
		try {
			System.out.println("1");
			String str = "Select * from admindb";
			state = con.createStatement();
			System.out.println("1");
			ResultSet rs = state.executeQuery(str);
			
			while(rs.next()) {
				if(id<rs.getInt(1))
				{
					id = rs.getInt(1);
				}
				
			}
			id=id+1;
			//System.out.println("1");
			pstate = con.prepareStatement("insert into admindb values(?,?,?)");
			
			pstate.setInt(1, id);
			pstate.setString(2, reg.getUname());
			pstate.setString(3, reg.getPassword());
			System.out.println("1");
			
			 i = pstate.executeUpdate();
			
			if(i>0) {
				System.out.println(reg.getUname() + " Registered!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	
	public int CustomerRegistration(Registration reg) {
		
		con = mycon.getConnection();
		int id = 0;
		
		try {
			System.out.println("1");
			String str = "Select * from customerdb";
			state = con.createStatement();
			System.out.println("1");
			ResultSet rs = state.executeQuery(str);
			
			while(rs.next()) {
				if(id<rs.getInt(1))
				{
					id = rs.getInt(1);
				}
			}
			id=id+1;
			//System.out.println("1");
			pstate = con.prepareStatement("insert into customerdb values(?,?,?,?,?,?)");
			
			pstate.setInt(1, id);
			pstate.setString(2, reg.getName());
			pstate.setString(3, reg.getUname());
			pstate.setInt(4, reg.getPhone());
			pstate.setString(5, reg.getEmail());
			pstate.setString(6, reg.getPassword());
			System.out.println("1");
			
			 i = pstate.executeUpdate();
			
			if(i>0) {
				System.out.println(reg.getUname() + " Registered!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
