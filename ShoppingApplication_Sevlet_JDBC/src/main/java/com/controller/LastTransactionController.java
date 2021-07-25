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
import com.model.LastTransaction;
import com.model.Product;

/**
 * Servlet implementation class LastTransactionController
 */
@WebServlet("/LastTransactionController")
public class LastTransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LastTransactionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CustomerDao custd = new CustomerDao();
		 HttpSession session = request.getSession();
		 String uname = (String) session.getAttribute("uname");
		 List<LastTransaction> trans = custd.lastransaction(uname);
		 PrintWriter pw = response.getWriter();
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
					+"<th>Bill</th>"
					+"<th>Amount</th>"
					+"</tr>";
			pw.print(htmlresponse);
	       for(LastTransaction p:trans) {
	        	 	pw.print("<tr>");
		            pw.print("<td>"+p.getBillNo()+"</td>");
		            pw.print("<td>"+p.getAmount()+"</td>");
		            pw.print("</tr>");
	        }
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
