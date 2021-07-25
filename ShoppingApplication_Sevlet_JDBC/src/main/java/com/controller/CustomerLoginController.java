package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		
		PrintWriter pw = response.getWriter();
		Login log = new Login(uname, password);
		LoginDao logdao = new LoginDao();
		
		String str = logdao.CustomerLogin(log);
		if(str!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("uname",uname);
			
			CustomerDao custdao=new CustomerDao();
			System.out.println("Login controller");
			
			
			response.sendRedirect("CustomerDashboard.html");
			
		}
		else {
			String htmlresponse ="<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Insert title here</title>\r\n"
					+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
					+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\r\n"
					+ "\r\n"
					+ "<style>\r\n"
					+ "      body{\r\n"
					+ "        font-family: 'Poppins', sans-serif;\r\n"
					+ "      }\r\n"
					+ "      \r\n"
					+ "      nav,\r\n"
					+ "      .top {\r\n"
					+ "        background-color: rgb(0,0,0);\r\n"
					+ "        \r\n"
					+ "      }\r\n"
					+ "      .border {\r\n"
					+ "        border-radius: 20px;\r\n"
					+ "        border-color: rgb(0, 4, 5);\r\n"
					+ "        border-width: 100px;\r\n"
					+ "        border-style: solid;\r\n"
					+ "      }\r\n"
					+ "\r\n"
					+ "      \r\n"
					+ "    </style>\r\n"
					+ "\r\n"
					+ "<style>\r\n"
					+ ".button{background-color:#0275d8;\r\n"
					+ "margin-left: 50px;\r\n"
					+ "padding : 15px;\r\n"
					+ "}\r\n"
					+ ".button:hover {\r\n"
					+ "box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "<nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n"
					+ "    <div class=\"container-fluid fw-bolder fs-4\">\r\n"
					+ "      <a class=\"navbar-brand\" href=\"CustomerDashboard.html\"><img src=\"https://user-images.githubusercontent.com/55631782/126907473-d11f3353-9cf4-4d56-9f54-b2d80a3ae6a3.png\" alt=\"Logo\" height=\"70px\"/></a>\r\n"
					+ "      <button\r\n"
					+ "        class=\"navbar-toggler\"\r\n"
					+ "        type=\"button\"\r\n"
					+ "        data-bs-toggle=\"collapse\"\r\n"
					+ "        data-bs-target=\"#navbarTogglerDemo02\"\r\n"
					+ "        aria-controls=\"navbarTogglerDemo02\"\r\n"
					+ "        aria-expanded=\"false\"\r\n"
					+ "        aria-label=\"Toggle navigation\"\r\n"
					+ "      >\r\n"
					+ "        <span class=\"navbar-toggler-icon\"></span>\r\n"
					+ "      </button>\r\n"
					+ "      <div class=\"collapse navbar-collapse\" id=\"navbarTogglerDemo02\">\r\n"
					+ "        <ul class=\"navbar-nav me-auto mb-2 mb-lg-0 \">\r\n"
					+ "          <li class=\"nav-item\">\r\n"
					+ "          	<div class=\"modal fade\" id=\"exampleModalToggle1\" aria-hidden=\"true\" aria-labelledby=\"exampleModalToggleLabel\" tabindex=\"-1\">\r\n"
					+ "			  <div class=\"modal-dialog modal-dialog-centered\">\r\n"
					+ "			    <div class=\"modal-content\">\r\n"
					+ "			      <div class=\"modal-header\">\r\n"
					+ "			        <h5 class=\"modal-title\" id=\"exampleModalToggleLabel\">Admin Login</h5>\r\n"
					+ "			        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
					+ "			      </div>\r\n"
					+ "			      <div class=\"modal-body\">\r\n"
					+ "			        <form action=\"AdminLoginController\" method=\"post\">\r\n"
					+ "					  <div class=\"mb-3\">\r\n"
					+ "					    <label for=\"exampleInputEmail1\" class=\"form-label\">User-Name : </label>\r\n"
					+ "					    <input type=\"text\" name=\"uname\" class=\"form-control\">\r\n"
					+ "					  </div>\r\n"
					+ "					  <div class=\"mb-3\">\r\n"
					+ "					    <label for=\"exampleInputPassword1\" class=\"form-label\">Password : </label>\r\n"
					+ "					    <input  type=\"password\" name=\"password\" class=\"form-control\">\r\n"
					+ "					  </div>\r\n"
					+ "					  <button type=\"submit\" class=\"btn btn-primary\">Login</button>\r\n"
					+ "					</form>\r\n"
					+ "			      </div>\r\n"
					+ "			      \r\n"
					+ "			    </div>\r\n"
					+ "			  </div>\r\n"
					+ "			</div>\r\n"
					+ "			\r\n"
					+ "			<a class=\"btn btn-primary button\" data-bs-toggle=\"modal\" href=\"#exampleModalToggle1\" role=\"button\">Admin Login</a>\r\n"
					+ "          </li>\r\n"
					+ "          \r\n"
					+ "          <li class=\"nav-item\">\r\n"
					+ "          	\r\n"
					+ "            <div class=\"modal fade\" id=\"exampleModalToggle2\" aria-hidden=\"true\" aria-labelledby=\"exampleModalToggleLabel\" tabindex=\"-1\">\r\n"
					+ "			  <div class=\"modal-dialog modal-dialog-centered\">\r\n"
					+ "			    <div class=\"modal-content\">\r\n"
					+ "			      <div class=\"modal-header\">\r\n"
					+ "			        <h5 class=\"modal-title\" id=\"exampleModalToggleLabel\">Customer Login</h5>\r\n"
					+ "			        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
					+ "			      </div>\r\n"
					+ "			      <div class=\"modal-body\">\r\n"
					+ "			        <form action=\"CustomerLoginController\" method=\"post\">\r\n"
					+ "					  <div class=\"mb-3\">\r\n"
					+ "					    <label for=\"exampleInputEmail1\" class=\"form-label\">User-Name : </label>\r\n"
					+ "					    <input type=\"text\" name=\"uname\" class=\"form-control\">\r\n"
					+ "					  </div>\r\n"
					+ "					  <div class=\"mb-3\">\r\n"
					+ "					    <label for=\"exampleInputPassword1\" class=\"form-label\">Password : </label>\r\n"
					+ "					    <input  type=\"password\" name=\"password\" class=\"form-control\">\r\n"
					+ "					  </div>\r\n"
					+ "					  <button type=\"submit\" class=\"btn btn-primary\">Login</button>\r\n"
					+ "					</form>\r\n"
					+ "			      </div>\r\n"
					+ "			      \r\n"
					+ "			    </div>\r\n"
					+ "			  </div>\r\n"
					+ "			</div>\r\n"
					+ "			\r\n"
					+ "			<a class=\"btn btn-primary button\" data-bs-toggle=\"modal\" href=\"#exampleModalToggle2\" role=\"button\">Customer Login</a>\r\n"
					+ "          </li>\r\n"
					+ "           <li class=\"nav-item\">\r\n"
					+ "\r\n"
					+ "			<a class=\"btn btn-primary button\"  href=\"Registration.html\" role=\"button\">Register</a>\r\n"
					+ "          </li>\r\n"
					+ "        </ul>\r\n"
					+ "      </div>\r\n"
					+ "    </div>\r\n"
					+ "  </nav>\r\n"
					+ "\r\n"
					+ "  \r\n"
					+ "          \r\n"
					+ "\r\n"
					+ "<div class='container'>"
					+ "	  <h1 class='display-2'>Invalid Credentials!!!</h1>"
					+ "	    </div>"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "" ;
			pw.print(htmlresponse);
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
