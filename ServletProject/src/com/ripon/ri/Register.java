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
 * Servlet implementation class Register
 */
//@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String n = request.getParameter("name");
		String p = request.getParameter("password");
		String e = request.getParameter("email");
		String c = request.getParameter("country");
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		      
		      PreparedStatement ps =con.prepareStatement("insert into registeruser (name,password,email,country) values(?,?,?,?)");  
		  
		      ps.setString(1, n);
		      ps.setString(2, p);
		      ps.setString(3, e);
		      ps.setString(4, c);
		      
		      
		      if(n=="" || p=="" || e==""){
	    		  pw.print("please fillup the necessary field");
	    		  RequestDispatcher rd = request.getRequestDispatcher("register.html");
	    		  rd.include(request,response);
	    	  }
		      else{
		    	  int i = ps.executeUpdate();
		    	  pw.print("Registration successful.....");
	    		  RequestDispatcher rd = request.getRequestDispatcher("/link.html");
	    		  rd.include(request,response);;
		      }
		     
		 }catch (Exception e2) {System.out.println(e2);}   
		pw.close();
		
	}  
		}  
