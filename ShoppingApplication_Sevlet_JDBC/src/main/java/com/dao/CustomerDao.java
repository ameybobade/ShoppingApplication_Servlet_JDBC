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
			  System.out.println(rs);
			  if(rs.next()) {
				     p = new Registration(rs.getString(2), rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));

			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return p;
	}
}
