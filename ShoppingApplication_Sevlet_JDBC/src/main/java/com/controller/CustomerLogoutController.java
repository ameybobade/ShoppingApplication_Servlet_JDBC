package com.controller;

import java.io.IOException;
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
 * Servlet implementation class CustomerLogoutController
 */
@WebServlet("/CustomerLogoutController")
public class CustomerLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		List<Product> Cartlist=(List<Product>) session.getAttribute("Cartlist");
		String uname=(String) session.getAttribute("uname");
		System.out.println(uname+"control");
		CustomerDao custdao=new CustomerDao();
		custdao.Logout(Cartlist,uname);
		
		
		//session.removeAttribute("uname");
		session.removeAttribute("Cartlist");
		session.invalidate();
		
		session = request.getSession(false);
		if(session==null)
		{
			response.sendRedirect("Home.html");
		}
		else
		{
			System.out.println("Not null");
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
