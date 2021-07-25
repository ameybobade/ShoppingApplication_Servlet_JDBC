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
				  if(rs.getInt(3)>0) {
				    Product p = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
					lst.add(p);
				  }
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return lst;
	}

	public int AddtoCart(int ProdId,int ProdQuant,String uname)
	{
		con=mycon.getConnection();
		String ProdName = null;
		int CustId=0;
		try {
			
			/*-----For Checking Quantity Avaibility---*/
			pstate=con.prepareStatement("select * from productdb where prodid=?");
			pstate.setInt(1, ProdId);
			
			ResultSet rs=pstate.executeQuery();
			while(rs.next())
			{
				ProdName=rs.getString(2);
				if(rs.getInt(3)<ProdQuant)
				{
					return 0;
				}
				
			}
			System.out.println("Quantiy checked "+ ProdName);
			/*----------------------*/
			
			/*-----For finding custid from uname---*/
			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
			pstate.setString(1, uname);
			
			rs=pstate.executeQuery();
			while(rs.next())
			{
				CustId=rs.getInt(1);
			}
			System.out.println("Found Custid "+CustId);
			/*---------------*/
			
			/*-----Add in cartdb---*/
			pstate=con.prepareStatement("select * from cartdb where (Custid=? and prodname=?)");
			pstate.setInt(1, CustId);
			pstate.setString(2, ProdName);
			
			rs=pstate.executeQuery();
			
			if(rs.next())
			{
				System.out.println("Update");
				/*-------Update cartdb-----------*/
				
				pstate=con.prepareStatement("update cartdb set ProdQuant=? where (Custid=? and prodname=?)");
				pstate.setInt(1, ProdQuant);
				pstate.setInt(2, CustId);
				pstate.setString(3, ProdName);
				
				int i=pstate.executeUpdate();
				if(i>0)
				{
					System.out.println("Updated");
				}
				
			}
			else
			{
				System.out.println("Insert");
				/*-------Insert in cartdb-----------*/
				pstate=con.prepareStatement("insert into cartdb values(?,?,?)");
				pstate.setInt(1, CustId);
				pstate.setString(2, ProdName);
				pstate.setInt(3, ProdQuant);
				
				int i=pstate.executeUpdate();
				if(i>0)
				{
					System.out.println("Inserted");
				}
			}
			/*---------------*/
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 1;
		
	}
	
	public List<Product> CartDisplay(String uname)
	{
		List<Product> Cartlist=new LinkedList<Product>();
		con=mycon.getConnection();
		int CustId = 0,ProdPrice=0,ProdQuant=0;
		String ProdName;
		try {
			/*-----For finding custid from uname---*/
			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
			pstate.setString(1, uname);
			
			ResultSet rs=pstate.executeQuery();
			
			while(rs.next())
			{
				CustId=rs.getInt(1);
			}
			System.out.println("Found Custid "+CustId);
			/*---------------*/
			/*-----For finding cart with custid---*/
//			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
//			pstate.setString(1, uname);
//			
//			ResultSet rs=pstate.executeQuery();
//			
//			while(rs.next())
//			{
//				CustId=rs.getInt(1);
//			}
//			System.out.println("Found Custid "+CustId);
			/*---------------*/
			/*-----For finding cart with custid---*/
			pstate=con.prepareStatement("select * from cartdb where Custid=?");
			pstate.setInt(1, CustId);
			
			rs=pstate.executeQuery();
			
			while(rs.next())
			{
				ProdName=rs.getString(2);
				ProdQuant=rs.getInt(3);
				
				pstate=con.prepareStatement("select * from productdb where ProdName=?");
				pstate.setString(1, ProdName);
				
				ResultSet rs1=pstate.executeQuery();
				while(rs1.next())
				{
					Product prod=new Product(0, ProdName, ProdQuant, rs1.getInt(4));
					Cartlist.add(prod);
				}
				
				
			}
			System.out.println("Found Custid "+CustId);
			/*---------------*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Cartlist;
		
	}
	public List<Product> bill(String uname){
		List<Product> Cartlist=new LinkedList<Product>();
		con=mycon.getConnection();
		int CustId = 0,ProdPrice=0,ProdQuant=0;
		String ProdName;
		try {
			/*-----For finding custid from uname---*/
			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
			pstate.setString(1, uname);
			
			ResultSet rs=pstate.executeQuery();
			
			while(rs.next())
			{
				CustId=rs.getInt(1);
			}
			System.out.println("Found Custid "+CustId);
			
//			System.out.println("Found Custid "+CustId);
			/*---------------*/
			/*-----For finding cart with custid---*/
			pstate=con.prepareStatement("select * from cartdb where Custid=?");
			pstate.setInt(1, CustId);
			
			rs=pstate.executeQuery();
			
			while(rs.next())
			{
				ProdName=rs.getString(2);
				ProdQuant=rs.getInt(3);
				
				pstate=con.prepareStatement("select * from productdb where ProdName=?");
				pstate.setString(1, ProdName);
				
				ResultSet rs1=pstate.executeQuery();
				while(rs1.next())
				{
					Product prod=new Product(ProdQuant*rs1.getInt(4), ProdName, ProdQuant, rs1.getInt(4));
					Cartlist.add(prod);
				}
				
				
			}
			System.out.println("Found Custid "+CustId);
			/*---------------*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Cartlist;
}
	
//	public void ftotaldata(double ftotal)
//	{
//		finaltotal=ftotal;
//		System.out.println(finaltotal);
//	}
	 public int paid(String uname,double finaltotal) {
		  con=mycon.getConnection();
		  int CustId = 0,ProdPrice=0,ProdQuant=0,i=0;
		  String ProdName=null;
		   try {
			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
	pstate.setString(1, uname);
			
			ResultSet rs=pstate.executeQuery();
			
			while(rs.next())
			{
				CustId=rs.getInt(1);
			}
			System.out.println("Found Custid "+CustId);
			
			pstate=con.prepareStatement("select * from cartdb where Custid=?");
			pstate.setInt(1, CustId);
			
			rs=pstate.executeQuery();
			
			while(rs.next())
			{
				ProdName=rs.getString(2);
				ProdQuant=rs.getInt(3);
				
				pstate=con.prepareStatement("select * from productdb where ProdName=?");
				pstate.setString(1, ProdName);
				
				ResultSet rs1=pstate.executeQuery();
				while(rs1.next())
				{
					int prodquant = rs1.getInt(3) -ProdQuant;
					pstate = con.prepareStatement("Update Productdb set PRODQUANT=? where PRODNAME=?");
					pstate.setInt(1, prodquant);
					pstate.setString(2, ProdName);
					i=pstate.executeUpdate();
 
				}
	
			}
			int billId=0;
			String str = "Select * from transactiondb";
			state = con.createStatement();
			System.out.println("1");
			rs = state.executeQuery(str);
			
			while(rs.next()) {
				if(billId<rs.getInt(2))
				{
					billId = rs.getInt(2);
				}
				
			}
			billId=billId+1;
			System.out.println(finaltotal);
			pstate = con.prepareStatement("insert into TransactionDB values(?,?,?)");
			pstate.setInt(1, CustId);
			pstate.setInt(2, billId);
			pstate.setDouble(3, finaltotal);
			pstate.executeUpdate();
			
			
			pstate = con.prepareStatement("delete from Cartdb where CUSTID=?");
			pstate.setInt(1, CustId);
			pstate.executeUpdate();
			System.out.println("Found Custid "+CustId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;   
		 
	 }

	public List<LastTransaction> lastransaction(String uname) {
		con=mycon.getConnection();
		  int CustId = 0,ProdPrice=0,ProdQuant=0,i=0;
		  List<LastTransaction> TransLst =new LinkedList<LastTransaction>();
		  String ProdName=null;
		   try {
			pstate=con.prepareStatement("select * from customerdb where CustUsername=?");
	pstate.setString(1, uname);
			
			ResultSet rs=pstate.executeQuery();
			
			while(rs.next())
			{
				CustId=rs.getInt(1);
			}
			System.out.println("Found Custid "+CustId);
			pstate = con.prepareStatement("select * from TransactionDB where CUSTID=?");
			pstate.setInt(1, CustId);
			rs = pstate.executeQuery();
			while(rs.next()) {
				LastTransaction t = new LastTransaction(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
				TransLst.add(t);
			}
	
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		   return TransLst;
	}
}