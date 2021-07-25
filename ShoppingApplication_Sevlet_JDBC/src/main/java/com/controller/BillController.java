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
 * Servlet implementation class BillController
 */
@WebServlet("/BillController")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		String uname = (String) session.getAttribute("uname");
		if(session==null)
		{
			response.sendRedirect("CustomerLogin.html");
		}
		double total=0,cgst=0,sgst,finaltotal=0;
		java.util.Date udate = new java.util.Date();
		System.out.println(udate);
		CustomerDao custdao=new CustomerDao();
		
		List<Product> CartList=custdao.bill(uname);
		
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
				+"<th>Total</th>"
				+"</tr>";
		pw.print(htmlresponse);
       for(Product p:CartList) {
        	 	pw.print("<tr>");
	            pw.print("<td>"+p.getProdName()+"</td>");
	            pw.print("<td>"+p.getProdQuant()+"</td>");
	            pw.print("<td>"+p.getProdPrice()+"</td>");
	            pw.print("<td>"+p.getProdId()+"</td>");
	            pw.print("</tr>");
	            total = total+(p.getProdId());
	             
        }
       
   	pw.print("<tr>");
    pw.print("<td>TOTAL</td>");
    pw.print("<td></td>");
    pw.print("<td></td>");
    pw.print("<td>"+total+"</td>");
    pw.print("</tr>");
              cgst=total*0.06;
		      sgst=total*0.06;
		      finaltotal = total+cgst+sgst;
		      
        pw.print("</table>");
        
        htmlresponse = "<br><br><br><table>"

                 + "<tr><td><label>DATE</label></td><td>"+udate+"</td></tr>"
        		+ "<tr><td><label>CGST</label></td><td>"+cgst+"</td></tr>"
        		+ "<tr><td><label>SGST</label></td><td>"+sgst+"</td></tr>"
        		+"<tr><td><label>FINAL TOTAL</label></td><td>"+finaltotal+"</td></tr>"
        		+ "</table><br><br>"
        		+ "<button><a href='PaidController'>Pay Bill</a></button><br><br>";

        
        htmlresponse += "	<button><a href=\"CustomerProfileController\">Profile</a></button>\r\n"
        		+ "	<!-- <button><a href=\"LastTransactionController\">Last Transaction</a></button> -->\r\n"
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
