package com.ripon.ri;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewItems
 */
//@WebServlet("/AddNewItems")
public class AddNewItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n = request.getParameter("name");
		String p = request.getParameter("price");
		String s = request.getParameter("status");
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		    PreparedStatement ps =con.prepareStatement("insert into productlist(name,price,status) values(?,?,?)");  
		  
		    ps.setString(1, n);
		    ps.setString(2, p);
		    ps.setString(3, s);
		    
		    if(n=="" || p=="" || s==""){
		    	out.print("please fillup necessary field");
		    }
		    else{
		    	int i = ps.executeUpdate();
		    	out.print("Update successful ");
		    	out.print("<br>");
		    	out.print("<a href='adminview.html'>Click For Home Page</a>");
		    	out.print("<br>");
		    }
		}
		catch (Exception e2) {System.out.println(e2);}
	
	}

}
