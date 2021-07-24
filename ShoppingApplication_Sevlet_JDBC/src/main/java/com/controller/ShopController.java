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

import com.dao.AdminDao;
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
		
		PrintWriter pw =  response.getWriter(); 
        CustomerDao ad = new CustomerDao();
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
        htmlRespone += "<form action='AddtoCartController' method='post'><label>Product Id:</label><input type=number name=prod_id><br><label>Product Quantity:</label><input type=number name=prod_quant><br><input type=submit value=Buy></form>";
        htmlRespone += "</body></html>";
        pw.println(htmlRespone);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
