package com.dao;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.model.*;

public class CustomerDao {
	Connection con = null;
	MyConnection mycon = new MyConnection();
	PreparedStatement pstate;
	Statement state;
	Registration p;
	
	public Registration profile(String name) {
		con = mycon.getConnection();
		Registration p=null;
	       try {
			  pstate = con.prepareStatement("select * from CustomerDB where CUSTUSERNAME=?");
			  pstate.setString(1, name);
			  ResultSet rs = pstate.executeQuery();
			  //System.out.println(rs);
			  if(rs.next()) {
				     p = new Registration(rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));

			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return p;
	}
	
	public List<Product> displayall(){
		   con = mycon.getConnection();
		   List<Product> lst = new LinkedList<Product>();
		   String str = "select * from ProductDB";
	       try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str);
			 ResultSetMetaData rm = rs.getMetaData();
			
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
	
	public List<Product> CheckCart(String uname)
	{
		List<Product> list = new LinkedList<Product>();
		con = mycon.getConnection();
		int CustId=0;
		String ProdName,ProdQuant;
		int ProdPrice = 0,ProdId=0;
		try {
			pstate = con.prepareStatement("Select * from Customerdb where CustUsername=?");
			
			pstate.setString(1, uname);
			
			ResultSet rs = pstate.executeQuery();
			if(rs.next())
			{
				CustId = rs.getInt(1);
				//System.out.println(CustId);
			}
			
			pstate = con.prepareStatement("select * from Cartdb where CustId=?");
			pstate.setInt(1, CustId);
			
			ResultSet rs1=pstate.executeQuery();
			while(rs1.next())
			{
				ProdName=rs.getString(2);
				pstate = con.prepareStatement("select * from Productdb where ProdName=?");
				pstate.setString(1, ProdName);
				
				ResultSet rs2 = pstate.executeQuery();
				while(rs2.next())
				{
					ProdId = rs.getInt(1);
					ProdPrice = rs.getInt(4);
					//System.out.println(CustId);
					System.out.println(ProdId);
					System.out.println(ProdPrice);
				}
				
				Product prod = new Product(ProdId,rs1.getString(2),rs1.getInt(3),ProdPrice);
				list.add(prod);
				
			}
			for(Product prod:list)
			{
				System.out.println("------------------");
				System.out.println(prod.getProdName());
				System.out.println(prod.getProdPrice());
			}
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Product GetProduct(Product prod)
	{
		con = mycon.getConnection();
		
		try {
			pstate=con.prepareStatement("Select * from productdb where prodid=?");
			pstate.setInt(1, prod.getProdId());
			
			ResultSet rs = pstate.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt(3)>=prod.getProdQuant())
				{
					prod.setProdName(rs.getString(2));
					prod.setProdPrice(rs.getInt(4));
				}
				else
				{
					prod.setProdQuant(rs.getInt(3));
					return prod;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return prod;
	}
	
	public void Logout(List<Product> Cartlist,String uname)
	{
		con=mycon.getConnection();
		int CustId=0;
		String ProdName,ProdQuant;
		
		try {
			pstate = con.prepareStatement("Select * from Customerdb where CustUsername=?");
			
			
			pstate.setString(1, uname);
			System.out.println(uname);
			ResultSet rs = pstate.executeQuery();
			if(rs.next())
			{
				CustId = rs.getInt(1);
				//System.out.println(CustId);
			}
			System.out.println(CustId);
			pstate=con.prepareStatement("delete * from cartdb where custid=?");
			pstate.setInt(1, CustId);
			
			for(Product prod:Cartlist)
			{
				pstate=con.prepareStatement("insert into cartdb values(?,?,?)");
				pstate.setInt(1, CustId);
				pstate.setString(2, prod.getProdName());
				pstate.setInt(3, prod.getProdQuant());
				
				pstate.executeQuery();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
