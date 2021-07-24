package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.*;
import java.util.*;
import com.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerProfileController
 */
@WebServlet("/CustomerProfileController")
public class CustomerProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    HttpSession session = request.getSession();
		    String uname = (String) session.getAttribute("uname");
		    CustomerDao cdao = new CustomerDao();
		    Registration p =cdao.profile(uname);
		    PrintWriter pw = response.getWriter();
		    pw.print("<style>");
            pw.print("table {  font-family: arial, sans-serif; border-collapse: collapse; width: 60%;}");
            pw.print("td, th {\r\n"
            		+ "  border: 1px solid #dddddd;\r\n"
            		+ "  text-align: left;\r\n"
            		+ "  padding: 8px;\r\n"
            		+ "}");
            pw.print("tr:nth-child(even) {\r\n"
            		+ "  background-color: #dddddd;\r\n"
            		+ "}");
            pw.print("</style>");
          //  System.out.println(p.getName());
          //  System.out.println(p.getPhone());
            pw.print("<table>");
            pw.print("<tr>");
            pw.print("<th>Profile</th>");
            pw.print("</tr>");
            pw.print("<tr>");
	        pw.print("<td>Name</td>");
	        pw.print("<td>"+p.getName()+"</td>");
	        pw.print("</tr>");
	        pw.print("<tr>");
	        pw.print("<td>UserName</td>");
	        pw.print("<td>"+p.getUname()+"</td>");
	        pw.print("</tr>");
	         pw.print("<tr>");
	            pw.print("<td>Phone No.</td>");
	            pw.print("<td>"+p.getPhone()+"</td>");
	            pw.print("</tr>");
	            pw.print("<tr>");
	            pw.print("<td>Email</td>");
	            pw.print("<td>"+p.getEmail()+"</td>");
	            pw.print("</tr>");
	            pw.print("</table>");
	            
            
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
