package com.dao;

import java.sql.*;
import java.util.*;

import com.model.Product;

public class AdminDao {
	
	Connection con = null;
	MyConnection mycon = new MyConnection();
	PreparedStatement pstate;
	Statement state;
	int i=0;
	
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
				if(prodId<rs.getInt(1))
				{
					prodId = rs.getInt(1);
				}
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
	public int deleteProduct(int id) {
		con = mycon.getConnection();
		  try {
			pstate = con.prepareStatement("delete from ProductDB where prodid=?");
		    pstate.setInt(1, id);
			i = pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<Product> displayall(){
		   con = mycon.getConnection();
		   List<Product> lst = new LinkedList<Product>();
		   String str = "select * from ProductDB";
	       try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			// ResultSetMetaData rm = rs.getMetaData();
			
			  while(rs.next()) {
				    Product p = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
					lst.add(p);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return lst;
	}
	
	public int UpdateProduct(Product prod)
	{
		
		con = mycon.getConnection();
		
		try {
			
			
			pstate = con.prepareStatement("update ProductDB set prodname=?, prodquant=?, prodprice=? where prodid=?");
			
			pstate.setString(1, prod.getProdName());
			pstate.setInt(2, prod.getProdQuant());
			pstate.setInt(3, prod.getProdPrice());
			pstate.setInt(4, prod.getProdId());
			
			int i = pstate.executeUpdate();
			
			return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
}