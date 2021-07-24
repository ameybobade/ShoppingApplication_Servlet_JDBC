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
		
		HttpSession session=request.getSession();
		List<Product> Cartlist=(List<Product>) session.getAttribute("Cartlist");
		
		PrintWriter pw =  response.getWriter(); 
        
        
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
        pw.print("<h1>Cart</h1>");
        pw.print("<table>");
        pw.print("<tr>");
        
        pw.print("<th>Product Name</th>");
        pw.print("<th>Quantity</th>"); 
        pw.print("<th>Price</th>");
        pw.print("</tr>");
        
        for(Product p:Cartlist) {
        	 pw.print("<tr>");
	            
	            pw.print("<td>"+p.getProdName()+"</td>");
	            pw.print("<td>"+p.getProdQuant()+"</td>");
	            pw.print("<td>"+p.getProdPrice()+"</td>");
	            pw.print("</tr>");
        }
        pw.print("</table>");
        String htmlRespone = "<html>\r\n"
        		+ "<head>\r\n"
        		+ "<meta charset=\"ISO-8859-1\">\r\n"
        		+ "<title>Insert title here</title>\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "	<button><a href=\"CustomerProfileController\">Profile</a></button>\r\n"
        		+ "	<!-- <button><a href=\"LastTransactionController\">Last Transaction</a></button> -->\r\n"
        		+ "	<button><a href=\"ShopController\">Buy Products</a></button>\r\n"
        		+ "	<button><a href=\"CartController\">Cart</a></button>\r\n"
        		+ "	<button><a href=\"BillController\">Bill</a></button>\r\n"
        		+ "	<button><a href=\"CustomerLogoutController\">Logout</a></button>\r\n"
        		+ "</body>\r\n"
        		+ "</html>";
        pw.println(htmlRespone);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
