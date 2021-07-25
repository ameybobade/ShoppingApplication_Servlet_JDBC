package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;
import com.model.Product;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("/ShopController")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		CustomerDao custdao=new CustomerDao();
		
		List<Product> ProdList=custdao.displayall();
		PrintWriter pw =  response.getWriter(); 
		String htmlresponse="<style>";
		htmlresponse+="table {  font-family: arial, sans-serif; border-collapse: collapse; width: 60%;}";
		htmlresponse+="td, th {\r\n"
		+ "  border: 1px solid #dddddd;\r\n"
		+ "  text-align: left;\r\n"
		+ "  padding: 8px;\r\n"
		+ "}";
		htmlresponse+="tr:nth-child(even) {\r\n"
        		+ "  background-color: #dddddd;\r\n"
        		+ "}";
		htmlresponse+="</style>";
		htmlresponse+="<table>"
				+"<tr>"
				+"<th>Product ID</th>"
				+"<th>Product Name</th>"
				+"<th>Quantity</th>"
				+"<th>Price</th>"
				+"</tr>";
		pw.print(htmlresponse);
       for(Product p:ProdList) {
        	 	pw.print("<tr>");
	            pw.print("<td>"+p.getProdId()+"</td>");
	            pw.print("<td>"+p.getProdName()+"</td>");
	            pw.print("<td>"+p.getProdQuant()+"</td>");
	            pw.print("<td>"+p.getProdPrice()+"</td>");
	            pw.print("</tr>");
        }
        pw.print("</table>");
        
        htmlresponse="<form action='AddtoCartController' method='post' >"
        		+ "<label>Product Id: </label><input type=number name='prodid'><br>"
        		+ "<label>Product Quantity: </label><input type=number name='prodquant'><br>"
        		+ "<input type=submit value=Buy>"
        		+ "</form>";
        
        pw.print(htmlresponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
