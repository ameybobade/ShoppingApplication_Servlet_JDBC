package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.model.Product;

/**
 * Servlet implementation class UpdateProductController
 */
@WebServlet("/UpdateProductController")
public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();  
		
		int prodid = Integer.parseInt(request.getParameter("prodid")) ;
		String prodname = request.getParameter("prodname");
		int prodquant = Integer.parseInt(request.getParameter("prodquant"));
		int prodprice = Integer.parseInt(request.getParameter("prodprice"));
		
		Product prod = new Product(prodid, prodname, prodquant, prodprice);
		
		AdminDao addao = new AdminDao();
		
		int i = addao.UpdateProduct(prod);
		HttpSession session = request.getSession(false);
		
		String uname = (String)session.getAttribute("uname");
		if(i>0)
		{
		
			String htmlRespone = "<html><body>";
	        htmlRespone += "<h2>Product Updated</h2>";
	        htmlRespone += "<br>";
	        htmlRespone += "<button><a href='AdminDashBoard.html'>Admin DashBoard</a></button>";
	        
	        htmlRespone += "<br>";
	        htmlRespone += "<button><a href='LogoutController'>Logout "+uname+"</a></button>";
	        
	        htmlRespone += "</body></html>";
	        out.println(htmlRespone);
			
		}
		else
		{
			String htmlRespone = "<html><body>";
	        htmlRespone += "<h2>Product can't be updated. Please check the details you entered.</h2>";
	        htmlRespone += "<br>";
	        htmlRespone += "<button><a href='AdminDashBoard.html'>Admin DashBoard</a></button>";
	        htmlRespone += "<br>";
	        htmlRespone += "<button><a href='LogoutController'>Logout "+uname+"</a></button>";
	        htmlRespone += "</body></html>";
	        out.println(htmlRespone);
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
