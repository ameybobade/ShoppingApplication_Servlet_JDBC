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
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session=request.getSession(false);
		String uname = (String) session.getAttribute("uname");
		if(session==null)
		{
			response.sendRedirect("CustomerLogin.html");
		}
		CustomerDao custdao=new CustomerDao();
		
		List<Product> CartList=custdao.CartDisplay(uname);
		
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
				+"<th>Product Name</th>"
				+"<th>Quantity</th>"
				+"<th>Price</th>"
				+"</tr>";
		pw.print(htmlresponse);
       for(Product p:CartList) {
        	 	pw.print("<tr>");
	            pw.print("<td>"+p.getProdName()+"</td>");
	            pw.print("<td>"+p.getProdQuant()+"</td>");
	            pw.print("<td>"+p.getProdPrice()+"</td>");
	            pw.print("</tr>");
        }
        pw.print("</table>");
        htmlresponse = "	<button><a href=\"CustomerProfileController\">Profile</a></button>\r\n"
        		+ "	 <button><a href='LastTransactionController'>Last Transaction</a></button> \r\n"
        		+ "	<button><a href=\"ShopController\">Buy Products</a></button>\r\n"
        		+ "	<button><a href=\"CartController\">Cart</a></button>\r\n"
        		+ "	<button><a href=\"BillController\">Bill</a></button>\r\n"
        		+ "	<button><a href=\"CustomerLogoutController\">Logout</a></button>" ;
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
