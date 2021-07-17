package com.dao;

import java.sql.*;

import com.model.Product;

public class AdminDao {
	
	Connection con = null;
	MyConnection mycon = new MyConnection();
	PreparedStatement pstate;
	Statement state;
	
	public int AddProduct(Product prod)
	{
		
		con = mycon.getConnection();
		int prodId=0;
		try {
			String str = "select * from productdb";
			state = con.createStatement();
			ResultSet rs = state.executeQuery(str);
			
			while(rs.next())
			{
				prodId = rs.getInt(1);
			}
			
			prodId=prodId+1;
			
			pstate = con.prepareStatement("insert into ProductDB values(?,?,?,?)");
			pstate.setInt(1, prodId);
			pstate.setString(2, prod.getProdName());
			pstate.setInt(3, prod.getProdQuant());
			pstate.setInt(4, prod.getProdPrice());
			
			int i = pstate.executeUpdate();
			
			return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
}
