package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.dao.*;
import com.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayProductController
 */
@WebServlet("/DisplayProductController")
public class DisplayProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        PrintWriter pw =  response.getWriter(); 
	            AdminDao ad = new AdminDao();
	            List<Product> lst = ad.displayall();
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
	            pw.print("<table>");
	            pw.print("<tr>");
	            pw.print("<th>Product ID</th>");
	            pw.print("<th>Product Name</th>");
	            pw.print("<th>Quantity</th>"); 
	            pw.print("<th>Price</th>");
	            pw.print("</tr>");
	            HttpSession session = request.getSession(false);
				
				String uname = (String)session.getAttribute("uname");
	            for(Product p:lst) {
	            	 pw.print("<tr>");
	 	            pw.print("<td>"+p.getProdId()+"</td>");
	 	            pw.print("<td>"+p.getProdName()+"</td>");
	 	            pw.print("<td>"+p.getProdQuant()+"</td>");
	 	            pw.print("<td>"+p.getProdPrice()+"</td>");
	 	            pw.print("</tr>");
	            }
	            pw.print("</table>");
	            String htmlRespone = "<html><body>";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='AdminDashBoard.html'>Admin DashBoard</a></button>";
		        htmlRespone += "</body></html>";
		        pw.println(htmlRespone);
                pw.print("<button><a href='LogoutController'>Logout "+uname+"</a></button>");
	            
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}