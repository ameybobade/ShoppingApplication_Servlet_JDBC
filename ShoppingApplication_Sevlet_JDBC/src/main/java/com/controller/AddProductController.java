package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDao;
import com.model.Product;

/**
 * Servlet implementation class AddProductController
 */
@WebServlet("/AddProductController")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();  
		
		String prodname = request.getParameter("prodname");
		int prodquant = Integer.parseInt(request.getParameter("prodquant"));
		int prodprice = Integer.parseInt(request.getParameter("prodprice"));
		
		Product prod = new Product(0, prodname, prodquant, prodprice);
		
		AdminDao addao = new AdminDao();
		
		int i = addao.AddProduct(prod);
		if(i>0)
		{
			
	        		String htmlRespone = "\r\n"
	        				+ "<!DOCTYPE html>\r\n"
	        				+ "<html>\r\n"
	        				+ "<head>\r\n"
	        				+ "<meta charset=\"ISO-8859-1\">\r\n"
	        				+ "<title>Customer Dashborad</title>\r\n"
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
	        				+ "		 .nav-item{\r\n"
	        				+ "				      margin-left:30px;\r\n"
	        				+ "				      }\r\n"
	        				+ "      \r\n"
	        				+ "      \r\n"
	        				+ "    </style>\r\n"
	        				+ "</head>\r\n"
	        				+ "<body>\r\n"
	        				+ "	\r\n"
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
	        				+ "          \r\n"
	        				+ "          <li class=\"nav-item\">\r\n"
	        				+ "            <a class=\"nav-link active\" href=\"addproductadmin.html\">Add Product</a>\r\n"
	        				+ "          </li>\r\n"
	        				+ "          <li class=\"nav-item\">\r\n"
	        				+ "            <a class=\"nav-link\" href=\"UpdateProductAdmin.html\">Update Product</a>\r\n"
	        				+ "          </li>\r\n"
	        				+ "          <li class=\"nav-item\">\r\n"
	        				+ "            <a class=\"nav-link\" href=\"deleteproductadmin.html\"> Delete Product</a>\r\n"
	        				+ "          </li>\r\n"
	        				+ "          <li class=\"nav-item\">\r\n"
	        				+ "            <a class=\"nav-link\" href=\"DisplayProductController\">Display Products</a>\r\n"
	        				+ "          </li>\r\n"
	        				+ "          <li class=\"nav-item\">\r\n"
	        				+ "            <a class=\"nav-link\" href=\"LogoutController\">Logout</a>\r\n"
	        				+ "          </li>\r\n"
	        				+ "        </ul>\r\n"
	        				+ "      </div>\r\n"
	        				+ "    </div>\r\n"
	        				+ "  </nav>\r\n"
	        				+ "   <br>\r\n"
	        				+ "   <br>\r\n"
	        				+ "   <br>\r\n"
	        				+ "   <div class=\"container\">\r\n"
	        				+ "     <h1 class=\"display-2\">Product Added!!</h1>"
	        				+ "   </div>\r\n"
	        				+ "\r\n"
	        				+ "</body>\r\n"
	        				+ "</html>\r\n"
	        				+ "";
			        out.println(htmlRespone);
	            
	                	

		}
		else
		{
			String htmlRespone = "\r\n"
    				+ "<!DOCTYPE html>\r\n"
    				+ "<html>\r\n"
    				+ "<head>\r\n"
    				+ "<meta charset=\"ISO-8859-1\">\r\n"
    				+ "<title>Customer Dashborad</title>\r\n"
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
    				+ "		 .nav-item{\r\n"
    				+ "				      margin-left:30px;\r\n"
    				+ "				      }\r\n"
    				+ "      \r\n"
    				+ "      \r\n"
    				+ "    </style>\r\n"
    				+ "</head>\r\n"
    				+ "<body>\r\n"
    				+ "	\r\n"
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
    				+ "          \r\n"
    				+ "          <li class=\"nav-item\">\r\n"
    				+ "            <a class=\"nav-link active \" href=\"addproductadmin.html\">Add Product</a>\r\n"
    				+ "          </li>\r\n"
    				+ "          <li class=\"nav-item\">\r\n"
    				+ "            <a class=\"nav-link \" href=\"UpdateProductAdmin.html\">Update Product</a>\r\n"
    				+ "          </li>\r\n"
    				+ "          <li class=\"nav-item\">\r\n"
    				+ "            <a class=\"nav-link\" href=\"deleteproductadmin.html\"> Delete Product</a>\r\n"
    				+ "          </li>\r\n"
    				+ "          <li class=\"nav-item\">\r\n"
    				+ "            <a class=\"nav-link\" href=\"DisplayProductController\">Display Products</a>\r\n"
    				+ "          </li>\r\n"
    				+ "          <li class=\"nav-item\">\r\n"
    				+ "            <a class=\"nav-link\" href=\"LogoutController\">Logout</a>\r\n"
    				+ "          </li>\r\n"
    				+ "        </ul>\r\n"
    				+ "      </div>\r\n"
    				+ "    </div>\r\n"
    				+ "  </nav>\r\n"
    				+ "   <br>\r\n"
    				+ "   <br>\r\n"
    				+ "   <br>\r\n"
    				+ "   <div class=\"container\">\r\n"
    				+ "     <h1 class=\"display-2\">Product Unable to add in our Inventory!!</h1>"
    				+ "   </div>\r\n"
    				+ "\r\n"
    				+ "</body>\r\n"
    				+ "</html>\r\n"
    				+ "";
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
