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
import com.dao.CustomerDao;
import com.model.Product;

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
		
		int prodid=Integer.parseInt(request.getParameter("prod_id"));
		int prodquant=Integer.parseInt(request.getParameter("prod_quant"));
		
		HttpSession session = request.getSession();
		if(session==null)
		{
			response.sendRedirect("CustomerLogin.html");
		}
		
		List<Product> Cartlist=(List<Product>) session.getAttribute("Cartlist");
		
		int check=0;
		if(Cartlist!=null)
		{
			for(Product p: Cartlist)
			{
				if(p.getProdId()==prodid)
				{
					//Update
					p.setProdQuant(prodquant);
					check=1;
					break;
				}
			}
		}
		
		
		if(check==0)
		{
			//Insert
			CustomerDao custdao = new CustomerDao();
			
			Product prod=new Product(prodid, null, prodquant,0);
			prod=custdao.GetProduct(prod);
			if(prod.getProdName()==null)
			{
				PrintWriter pw =  response.getWriter();
				
				String htmlRespone = "<html><body>";
		        htmlRespone += "Quanity you mentioned is not available! Please try again later<br>";
		        htmlRespone += "Currently we have "+prod.getProdQuant()+"Qauntity";
		        htmlRespone += "<br>";
		        htmlRespone += "<button><a href='CustomerDashBoard.html'>Customer DashBoard</a></button>";
		        htmlRespone += "<button><a href='ShopController'>Add Product</a></button>";
		        htmlRespone += "<br>";
		        htmlRespone += "</body></html>";
		        pw.println(htmlRespone);
			}
			else
			{
				Cartlist.add(prod);
				session.setAttribute("Cartlist", Cartlist);
				response.sendRedirect("CartController");
//				PrintWriter pw =  response.getWriter();
//				
//				String htmlRespone = "<html><body>";
//		        htmlRespone += "Product Added to Cart successfully!";
//		        htmlRespone += "<br>";
//		        htmlRespone += "<button><a href='CustomerDashBoard.html'>Customer DashBoard</a></button>";
//		        htmlRespone += "<button><a href='ShopController'>Add Product</a></button>";
//		        htmlRespone += "<br>";
//		        htmlRespone += "</body></html>";
//		        pw.println(htmlRespone);
			}
			
			for(Product p: Cartlist)
			{
				System.out.println(p.getProdName()+" "+p.getProdQuant()+"\n");
			}
		}
		
		
		
		
		PrintWriter pw =  response.getWriter(); 
		pw.print("Product Id: "+prodid+" Product Quantity: "+prodquant);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
