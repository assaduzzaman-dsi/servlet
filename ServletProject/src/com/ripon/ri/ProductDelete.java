package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductDelete
 */
@WebServlet("/ProductDelete")
public class ProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String sd = request.getParameter("id");
		int id = Integer.parseInt(sd);
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		   PreparedStatement ps =con.prepareStatement("delete from productlist where id=?");
		   ps.setInt(1, id);
		   
		   int i = ps.executeUpdate();
		   
		   out.print("Delete successful"); 
	    	out.print("<br>");
	    	out.println("<a href='adminview.html'>Click For Home Page</a>");
		}
		catch(Exception e){e.printStackTrace();}  
	}

}
