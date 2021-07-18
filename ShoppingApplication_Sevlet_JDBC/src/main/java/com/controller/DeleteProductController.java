package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet("/DeleteProductController")
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter pw = response.getWriter();
		  int id = Integer.parseInt(request.getParameter("delete"));
		  AdminDao ad = new AdminDao();
		  int i = ad.deleteProduct(id);
		  HttpSession session = request.getSession(false);
			
			String uname = (String)session.getAttribute("uname");
			if(i>0)
			{
			
				String htmlRespone = "<html><body>";
		        htmlRespone += "<h2>Product Deleted Successfully.</h2>";
		        htmlRespone += "<br>";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='AdminDashBoard.html'>Admin DashBoard</a></button>";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='LogoutController'>Logout "+uname+"</a></button>";
		        
		        htmlRespone += "</body></html>";
		        pw.println(htmlRespone);
				
			}
			else
			{
				String htmlRespone = "<html><body>";
		        htmlRespone += "<h2>Product can't be deleted. Please check the details you entered.</h2>";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='AdminDashBoard.html'>Admin DashBoard</a></button>";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='LogoutController'>Logout "+uname+"</a></button>";
		        htmlRespone += "</body></html>";
		        pw.println(htmlRespone);
			}
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}