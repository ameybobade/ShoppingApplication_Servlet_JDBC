package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CustomerDao;

/**
 * Servlet implementation class AddtoCartController
 */
@WebServlet("/AddtoCartController")
public class AddtoCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int prodid=Integer.parseInt(request.getParameter("prodid"));
		int prodquant=Integer.parseInt(request.getParameter("prodquant"));
		HttpSession session = request.getSession(false);
		String uname=(String) session.getAttribute("uname");
		if(session==null)
		{
			response.sendRedirect("CustomerLogin.html");
		}
		CustomerDao custdao=new CustomerDao();
		int i=custdao.AddtoCart(prodid, prodquant,uname);
		
		PrintWriter pw=response.getWriter();
		if(i==0)
		{
			
			pw.print("InSufficient Quantity");
		}
		else
		{
			System.out.println("Added");
			response.sendRedirect("CartController");
		}
//		PrintWriter pw=response.getWriter();
//		pw.print(prodid+" "+prodquant);
		System.out.println("Addtocart"+prodid+" "+prodquant);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
