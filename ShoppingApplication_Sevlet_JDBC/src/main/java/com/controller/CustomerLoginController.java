package com.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;
import com.dao.LoginDao;
import com.model.Login;
import com.model.Product;

/**
 * Servlet implementation class CustomerLoginController
 */

@WebServlet("/CustomerLoginController")
public class CustomerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	List<Product> Cartlist = null;
    public void init() {
    	Cartlist = new LinkedList<Product>();
    	System.out.println("init here");
  	}
    public CustomerLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		
		
		Login log = new Login(uname, password);
		LoginDao logdao = new LoginDao();
		
		String str = logdao.CustomerLogin(log);
		if(str!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("uname",uname);
			
			CustomerDao custdao=new CustomerDao();
			Cartlist = custdao.CheckCart(uname);
			
			session.setAttribute("Cartlist",Cartlist);
			
			response.sendRedirect("CustomerDashboard.html");
			
		}
		else {
			response.getWriter().append("Invalid uname or password").append(request.getContextPath());
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
